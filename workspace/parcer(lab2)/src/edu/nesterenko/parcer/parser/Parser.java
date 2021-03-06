package edu.nesterenko.parcer.parser;

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
import edu.nesterenko.parcer.exception.PhisicalException;

public class Parser {
	private Component root = new Composite("root");
	
	private Map<String, List<String>> fatherChildrenMap = new HashMap<String, List<String>>();
	private Map<String, String> elementNamePatternMap = new HashMap<String, String>();
	private Map<String, String> elementNameTypeMap = new HashMap<String, String>();
	
	public void configure(String propertiesFilePath) throws LogicalException, PhisicalException {
		elementNameTypeMap.put("root", "composite");
		Properties properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(propertiesFilePath);
			properties.load(fis);
		} catch(IOException e) {
			throw new PhisicalException(e);
		}		
		int i = 1;
		String elementName;
		while((elementName = properties.getProperty("elem" + i + "." + "name")) != null) {
			String elementPattern = properties.getProperty("elem" + i + "." + "pattern");
			String elementType = properties.getProperty("elem" + i + "." + "type");
			String elementFather = properties.getProperty("elem" + i + "." + "father");
			if(elementPattern == null || elementType == null || elementFather == null) {
				throw new LogicalException("Bad configuration");
			}
			if(!"leaf".equals(elementType) && !"composite".equals(elementType)) {
				throw new LogicalException("this element type:" + "'" + elementType + "'" + " can not be recognized");
			}
			elementNamePatternMap.put(elementName, elementPattern);
			elementNameTypeMap.put(elementName, elementType);
			List<String> childrensList = fatherChildrenMap.get(elementFather);
			if(childrensList == null) {
				childrensList = new ArrayList<String>();
				childrensList.add(elementName);
				fatherChildrenMap.put(elementFather, childrensList);
			} else {
				childrensList.add(elementName);
			}		
			i++;
		}
	}
	
	public void parse(String text) throws LogicalException {
		parse(text, root);
	}
	
	private void parse(String text, Component father) throws LogicalException {
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
					if(elementNamePatternMap.get(child).isEmpty()) {
						elementName = child;
						break;
					}
				} 
				if(elementName == null) {
					throw new LogicalException("Unidentifiable symbols sequence:" + "'"+ findedText+"'" );
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
			if(elementName == null) {
				throw new LogicalException("Unidentifiable symbols sequence:" + "'"+ findedText+"'" );
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
				if(elementNamePatternMap.get(child).isEmpty()) {
					elementName = child;
					break;
				}
			} 
			if(elementName == null) {
				throw new LogicalException("Unidentifiable symbols sequence:" + "'"+ findedText +"'" );
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
			parse(findedText, composite);
			father.addComponent(composite);
			break;			
		}
	}
	
	public Component getHierarchy() {
		return root;
	}
	
	public String reviveText() throws LogicalException {
		return TextReviver.reviveText(root);
	}
	
}
