package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import java.util.List;
import edu.nesterenko.touroperator.dao.CountryDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class CountryListPageCommand implements Command {

	
	private final static Logger LOG = Logger.getLogger(CountryListPageCommand.class);
	private static CountryListPageCommand instance = new CountryListPageCommand();
	
	private CountryListPageCommand() {}

	public static CountryListPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			CountryDao countryDao = new CountryDao();
			List<Country> countryList = null;
			try {
				countryList = countryDao.findAll();
			} catch (DaoException e) {
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
