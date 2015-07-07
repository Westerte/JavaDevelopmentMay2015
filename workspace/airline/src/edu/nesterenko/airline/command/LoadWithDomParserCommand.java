package edu.nesterenko.airline.command;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.dao.DomParserDao;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirlineAirplainsFinder;
import edu.nesterenko.airline.logic.Loader;

public class LoadWithDomParserCommand implements Command {
	private final static Logger LOG = Logger.getLogger(LoadWithDomParserCommand.class);
	private static LoadWithDomParserCommand instance;
	
	private LoadWithDomParserCommand() {}
	
	static {
		instance = new LoadWithDomParserCommand();
	}
	
	public static LoadWithDomParserCommand getInstance() {
		return instance;
	}
	@Override
	public String execute(HttpServletRequest request) {
		try {
			String xmlFilePath = request.getServletContext().getRealPath("") + File.separator + "xml/AirlineXml.xml";
			Loader.load(new DomParserDao(), xmlFilePath);
			request.setAttribute("airplanes", AirlineAirplainsFinder.findAll());
			request.setAttribute("is_ok", true);
		} catch (PhisicalException | LogicalException e) {
			request.setAttribute("is_ok", false);
			LOG.error(e);
		}
		return "jsp/airplains.jsp";
	}

}
