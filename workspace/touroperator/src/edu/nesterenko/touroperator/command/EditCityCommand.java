package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.logic.CityLogic;
import edu.nesterenko.touroperator.logic.CountryLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class EditCityCommand implements Command {
	private final static Logger LOG = Logger.getLogger(EditCityCommand.class);
	private static EditCityCommand instance = new EditCityCommand();
	
	private EditCityCommand() {}
	
	public static EditCityCommand getInstance() {
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
			String description = request.getParameter("description");
			String countryId = request.getParameter("country");
			try {
				CityLogic.editCity(id, name, description, countryId);				
				List<City> cityList = CityLogic.findAll();
				List<Country> countryList = CountryLogic.findAll();
				request.setAttribute("cityList", cityList);
				request.setAttribute("countryList", countryList);
				jspPath = ConfigurationManager.getProperty("path.page.cityList");
			} catch (LogicException e) {
				request.setAttribute("id", id);
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				request.setAttribute("country", countryId);
				LOG.error(e);
				jspPath = ConfigurationManager.getProperty("path.page.cityEditPage");
			}			
			
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}

}