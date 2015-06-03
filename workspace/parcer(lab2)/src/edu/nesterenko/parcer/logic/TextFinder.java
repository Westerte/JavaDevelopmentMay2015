package edu.nesterenko.parcer.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.nesterenko.parcer.entity.Component;
import edu.nesterenko.parcer.entity.Composite;
import edu.nesterenko.parcer.entity.Leaf;
import edu.nesterenko.parcer.exception.LogicalException;
import edu.nesterenko.parcer.exception.PhisicalException;

public class TextFinder {
	public static List<String> findSentenceOrderedByWordCount(Parser parser) throws PhisicalException, LogicalException  {
		Component root = parser.getHierarchy();
		Map<Component, Integer> sentenceWordCountMap = new HashMap<Component, Integer>();
		findSentences(root, sentenceWordCountMap);
		List<Map.Entry<Component, Integer>> entrySetList = new ArrayList<Map.Entry<Component, Integer>>(sentenceWordCountMap.entrySet());
		Comparator<Map.Entry<Component, Integer>> comp = 
				(Map.Entry<Component, Integer> o1, Map.Entry<Component, Integer> o2) 
				-> 
				o1.getValue() - o2.getValue();
		Collections.sort(entrySetList, comp);
		List<String> result = new ArrayList<String>();
		for(Map.Entry<Component, Integer> entry : entrySetList) {
			Component component = entry.getKey();
			String revivedString = TextReviver.reviveText(component);
			revivedString = revivedString.replaceAll("(\\r*\\n)", " ");
			result.add(revivedString);
		}
		return result;		
	}
	private static void findSentences(Component father, Map<Component, Integer>  sentenceWordCountMap) throws PhisicalException {
		for(Component component : father) {
			if("sentence".equals(component.getName())){
				sentenceWordCountMap.put(component, takeComponentsCountOfType("word", (Composite)component));
				continue;
			}
			if(component instanceof Composite) {
				findSentences(component, sentenceWordCountMap);
			}			
		}
	}
	private static int takeComponentsCountOfType(String name, Composite composite) throws PhisicalException {
		if(null == name || null == composite) {
			throw new PhisicalException("name and composite must be not null");
		}
		int result = 0;
		for(Component component : composite) {
			if(name.equals(component.getName())) { 
				result++;
			}
		}
		return result;
	}
}
