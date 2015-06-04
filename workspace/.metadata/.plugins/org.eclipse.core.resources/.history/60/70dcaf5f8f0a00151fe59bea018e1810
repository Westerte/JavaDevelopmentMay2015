package edu.nesterenko.parcer.logic;

import edu.nesterenko.parcer.entity.Component;
import edu.nesterenko.parcer.entity.Composite;
import edu.nesterenko.parcer.entity.Leaf;
import edu.nesterenko.parcer.exception.LogicalException;

public class TextReviver {
	
	public static String reviveText(Component component) throws LogicalException {
		StringBuilder stringBuilder = new StringBuilder();
		reviveText(component, stringBuilder);
		return stringBuilder.toString();
	}
	
	private static void reviveText(Component component, StringBuilder stringBuilder) throws LogicalException {
		if(component instanceof Composite) {
			for(Component children: component) {
				reviveText(children,stringBuilder);
			}
		} else {
			if(component instanceof Leaf) {
				stringBuilder.append(component.getContent());
			} else {
				throw new LogicalException("this element type can not be recognized");
			}
		}
	}
}
