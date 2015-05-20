package com.epam.five.run;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

class FirstLogRunner {
	final static Logger LOG = Logger.getLogger(FirstLogRunner.class);
	
	static {
		//initWithXml();
	    initWithProperties();
	}
	
	public static void main(String[] args) {
		LOG.info("info");     
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