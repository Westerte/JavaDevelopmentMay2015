package edu.nesterenko.parcer.logic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.nesterenko.parcer.entity.Component;
import edu.nesterenko.parcer.entity.Composite;
import edu.nesterenko.parcer.exception.LogicalException;
import edu.nesterenko.parcer.exception.PhisicalException;
import edu.nesterenko.parcer.parser.Parser;

public class TextEditor {
	
	static final Set<Character> CONSONANTS_SET;
	
	static {
		CONSONANTS_SET = new HashSet<Character>();
		char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 
					'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
		for(Character consonant : consonants) {
			CONSONANTS_SET.add(consonant);
		}
	}
	
	
	public static void swapFirstAndLastWords(Parser parser) 
			throws LogicalException, PhisicalException {
		Component root = parser.getHierarchy();
		findSentencesAndSwapWords(root);
	}
	
	public static void deleteWordsStartingWithConsonant(Parser parser, int length) 
			throws LogicalException, PhisicalException {
		Component root = parser.getHierarchy();
		findSentencesAndDeleteWords(root, length);
	}	
	
	private static void findSentencesAndDeleteWords(Component father, int length) 
			throws  LogicalException, PhisicalException {
		for(Component component : father) {
			if("sentence".equals(component.getName())){
				deleteWords(component, length);
				continue;
			}
			if(component instanceof Composite) {
				findSentencesAndDeleteWords(component, length);
			}	
		}
	}
	
	private static void deleteWords(Component composite, int length) throws LogicalException, PhisicalException {
		List<Component> markToRemove = new LinkedList<Component>();
		for(Component child : composite) {
			if("word".equals(child.getName()) && child.getContent().length() == length &&
					CONSONANTS_SET.contains(child.getContent().toLowerCase().charAt(0))) {
				markToRemove.add(child);
			}
		}
		for(Component child : markToRemove) {
			composite.removeComponent(child);
		}
	}
	
	private static void findSentencesAndSwapWords(Component father) throws PhisicalException, LogicalException {
		for(Component component : father) {
			if("sentence".equals(component.getName())){
				swapWords(component);
				continue;
			}
			if(component instanceof Composite) {
				findSentencesAndSwapWords(component);
			}	
		}
	}
	
	private static void swapWords(Component component) throws PhisicalException, LogicalException {
		int i = 0;
		while (!"word".equals(component.getComponent(i).getName()) &&  i < component.size()) {
			i++;
		}
		if(i < component.size()) {
			Component firstWord = component.getComponent(i);
			int firstWordIndex = i;
			i = component.size() - 1;
			while (!"word".equals(component.getComponent(i).getName()) &&  i >= 0) {
				i--;
			}
			if(i >= 0) {
				int lastWordIndex = i;
				component.setComponent(firstWordIndex, component.getComponent(lastWordIndex));
				component.setComponent(lastWordIndex, firstWord);
			}
		}
	}
}
