package com.epam.five.run;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

class FirstLogRunner {
	final static Logger LOG = Logger.getLogger(FirstLogRunner.class);
	
	static {
		//initWithXml();
	    initWithProperties();
	}
	
	public static void main(String[] args) {
		/*Locale ru = new Locale("ru", "RU");  
		ResourceBundle rb = ResourceBundle.getBundle("resource.ApplicationResources" , ru);  
		System.out.println(ru.getDisplayLanguage());
		String s1 = rb.getString("str1");
		System.out.println(s1);
		Locale en = new Locale("en", "GB");  
		ResourceBundle rb1 = ResourceBundle.getBundle("resource.ApplicationResources", en); 
		String s2 = rb1.getString("str1");
		System.out.println(s2);
		Locale by = new Locale("be", "BY");  
		ResourceBundle rb2 = ResourceBundle.getBundle("resource.ApplicationResources", by);  
		String s3 = rb2.getString("str1");
		System.out.println(s3); */		
		Vadison[] items = {new Vadison(1),new Vadison(2)};
		Vadison[] newItems = items.clone();
		newItems[0].value = 3;
		System.out.println(newItems[0].value + " " + items[0].value);
	}
	
	private static void initWithXml() {
		new DOMConfigurator().doConfigure("config/log4j.xml", LogManager.getLoggerRepository());
	}
	
	private static void initWithProperties(){
		Properties logProperties = new Properties();
		try {
			logProperties.load(new FileInputStream("config/log4j.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
	    PropertyConfigurator.configure(logProperties);
	}
}

class Vadison {
	int value;
	Vadison(int value) {
		this.value = value;
	}
}
