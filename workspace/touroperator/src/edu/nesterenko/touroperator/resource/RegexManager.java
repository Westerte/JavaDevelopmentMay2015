package edu.nesterenko.touroperator.resource;

import java.util.ResourceBundle;

public class RegexManager {
	
	private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resources.regexpatterns");
	
	public static String getProperty(String key) {
		return RESOURCE_BUNDLE.getString(key);
	}
}
