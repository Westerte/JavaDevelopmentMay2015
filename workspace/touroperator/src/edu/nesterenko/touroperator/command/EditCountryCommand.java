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

public class EditCountryCommand implements Command {
	private final static Logger LOG = 
			Logger.getLogger(EditCountryCommand.class);
	private static EditCountryCommand instance = new EditCountryCommand();
	
	private EditCountryCommand() {}
	
	public static EditCountryCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			try {				
				CountryLogic.editCountry(id, name);					
				List<Country> countryList = CountryLogic.findAll();
				request.setAttribute("countryList", countryList);				
				jspPath = ConfigurationManager.getProperty("path.page.countryList");
			} catch (LogicException e) {
				request.setAttribute("id", id);
				request.setAttribute("name", name);
				LOG.error(e);
				jspPath = ConfigurationManager.getProperty("path.page.countryEditPage");
			}
			
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}

}
