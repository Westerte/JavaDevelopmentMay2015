package edu.nesterenko.airline.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.command.CommandEnum;
import edu.nesterenko.airline.command.CommandFactory;
import edu.nesterenko.airline.exception.LogicalException;

public class Controller {
	private final static Logger LOG = Logger.getLogger(Controller.class);
	
	static {
		new DOMConfigurator().doConfigure("config/log4j.xml", LogManager.getLoggerRepository());
	}
	
	public Response sendReques(CommandEnum command,Request request) {
		Response response = new Response();
		try {
			response = CommandFactory.getCommand(command).processRequest(request);
		} catch (LogicalException e) {
			LOG.error(e);
		}
		return response;
	}
}
