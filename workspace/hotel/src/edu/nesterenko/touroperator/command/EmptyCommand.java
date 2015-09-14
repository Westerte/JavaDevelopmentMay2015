package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class EmptyCommand implements Command {
	private final static Logger LOG = Logger.getLogger(EmptyCommand.class);
	private static EmptyCommand instance = new EmptyCommand();
	
	private EmptyCommand() {}
	
	public static EmptyCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		LOG.error("Direct request to controller or bad commandName");
		String pagePath = ConfigurationManager.getProperty("path.page.index"); 
		return pagePath;
	}

}
