package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.logic.CountryLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class AddCountryCommand implements Command {
	private final static Logger LOG = 
			Logger.getLogger(AddCountryCommand.class);
	private static AddCountryCommand instance = new AddCountryCommand();
	
	private AddCountryCommand() {}
	
	public static AddCountryCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			List<Country> countryList = null;
			String name = request.getParameter("name");
			try {				
				CountryLogic.addCountry(name);				
			} catch (LogicException e) {
				LOG.error(e);
			}
			try {
				countryList = CountryLogic.findAll();
			} catch (LogicException e) {
				LOG.error(e);
			}
			request.setAttribute("countryList", countryList);
			jspPath = ConfigurationManager.getProperty("path.page.countryList");
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}

}
