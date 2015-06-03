package edu.nesterenko.parcer.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nesterenko.parcer.entity.Component;
import edu.nesterenko.parcer.entity.Composite;
import edu.nesterenko.parcer.entity.Leaf;
import edu.nesterenko.parcer.exception.LogicalException;

public class Parser {
	private Component root = new Composite("root");
	
	private Map<String, List<String>> fatherChildrenMap = new HashMap<String, List<String>>();
	private Map<String, String> elementNamePatternMap = new HashMap<String, String>();
	private Map<String, String> elementNameTypeMap = new HashMap<String, String>();
	
	public void configure(String propertiesFilePath) throws IOException, LogicalException {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(propertiesFilePath);
		properties.load(fis);
		elementNameTypeMap.put("root", "composite");
		int i = 1;
		String elementName;
		while((elementName = properties.getProperty("elem"+i+"."+"name")) != null) {
			String elementPattern = properties.getProperty("elem"+i+"."+"pattern");
			String elementType = properties.getProperty("elem"+i+"."+"type");
			String elementFather = properties.getProperty("elem"+i+"."+"father");
			if(elementPattern == null || elementType == null || elementFather == null) {
				throw new LogicalException("Bad configuration");
			}
			elementNamePatternMap.put(elementName, elementPattern);
			elementNameTypeMap.put(elementName, elementType);
			List<String> childrensList = fatherChildrenMap.get(elementFather);
			if(null == childrensList) {
				childrensList = new ArrayList<String>();
				childrensList.add(elementName);
				fatherChildrenMap.put(elementFather, childrensList);
			} else {
				childrensList.add(elementName);
			}		
			i++;
		}
	}
	
	public void parce(String text) throws LogicalException {
		parce(text, root);
	}
	
	private void parce(String text, Component father) throws LogicalException {
		List<String> childrensNames = fatherChildrenMap.get(father.getName());
		String regEx = "";
		for(int i = 0 ; i < childrensNames.size(); i++) {
			String childPattern = elementNamePatternMap.get(childrensNames.get(i));
			if("".equals(childPattern)){
				continue;
			}
			regEx += "|" + "(" + childPattern + ")";
		}
		regEx = regEx.substring(1);
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(text);
		int currentStepStart = 0;
		int prevStepEnd = 0;
		while(matcher.find(prevStepEnd)) {
			currentStepStart = matcher.start();
			String findedText;
			if(prevStepEnd - currentStepStart != 0) {
				findedText = text.substring(prevStepEnd, currentStepStart);
				String elementName = null;
				for(String child : childrensNames) {
					if("".equals(elementNamePatternMap.get(child))) {
						elementName = child;
						break;
					}
				} 
				if(null == elementName) {
					throw new LogicalException("Unidentifiable symbols siquence");
				}
				buildComponent(findedText, father, elementName);
			}
			prevStepEnd = matcher.end();
			findedText = matcher.group();
			String elementName = null;
			for(String child : childrensNames) {
				Pattern ChildPattern = Pattern.compile(elementNamePatternMap.get(child));
				matcher.usePattern(ChildPattern);
				if(matcher.find(currentStepStart) && matcher.end() == prevStepEnd) {
					elementName = child;
					break;
				}
			} 
			if(null == elementName) {
				throw new LogicalException("Unidentifiable symbols siquence");
			} else {
				matcher.usePattern(pattern);
			}
			buildComponent(findedText, father, elementName);			
		}
		int textLength = text.length();
		if(prevStepEnd - textLength != 0) {
			String findedText = text.substring(prevStepEnd, textLength);
			String elementName = null;
			for(String child : childrensNames) {
				if("".equals(elementNamePatternMap.get(child))) {
					elementName = child;
					break;
				}
			} 
			if(null == elementName) {
				throw new LogicalException("Unidentifiable symbols siquence");
			}
			buildComponent(findedText, father, elementName);
		}		
	}	
	
	private void buildComponent(String findedText, Component father, String elementName) throws LogicalException {
		switch (elementNameTypeMap.get(elementName)) {
		case "leaf":
			Component leaf = new Leaf(elementName, findedText);
			father.addComponent(leaf);
			break;
		case "composite":
			Component composite = new Composite(elementName);
			parce(findedText, composite);
			father.addComponent(composite);
			break;
		default: 
			throw new LogicalException("this element type can not be recognized");
		}
	}
	
	public Component getHierarchy() {
		return root;
	}
	
	public String reviveText() throws LogicalException {
		return TextReviver.reviveText(root);
	}
	
}