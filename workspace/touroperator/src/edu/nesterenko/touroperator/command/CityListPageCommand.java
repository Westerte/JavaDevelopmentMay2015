package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.dao.CityDao;
import edu.nesterenko.touroperator.dao.CountryDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.Country;
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
			CityDao cityDao = new CityDao();
			CountryDao countryDao = new CountryDao();
			List<City> cityList = null;	
			List<Country> countryList = null;
			try {
				cityList = cityDao.findAll();
				countryList = countryDao.findAll();
			} catch (DaoException e) {
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
