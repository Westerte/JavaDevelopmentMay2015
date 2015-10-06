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

public class CityListPageCommand implements Command {
	
	private final static Logger LOG = Logger.getLogger(CityListPageCommand.class);
	private static CityListPageCommand instance = new CityListPageCommand();
	
	private CityListPageCommand() {}

	public static CityListPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			List<City> cityList = null;	
			List<Country> countryList = null;
			try {
				cityList = CityLogic.findAll();
				countryList = CountryLogic.findAll();
			} catch (LogicException e) {
				LOG.error(e);
			}
			request.setAttribute("cityList", cityList);
			request.setAttribute("countryList", countryList);			
			jspPath = ConfigurationManager.getProperty("path.page.cityList");
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		
		return jspPath;
	}

}