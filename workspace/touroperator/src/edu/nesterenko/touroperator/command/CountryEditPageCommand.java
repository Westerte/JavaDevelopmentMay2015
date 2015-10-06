package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.logic.CountryLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;


public class CountryEditPageCommand  implements Command {
	private final static Logger LOG = Logger.getLogger(CountryEditPageCommand .class);
	private static CountryEditPageCommand  instance = new CountryEditPageCommand ();
	
	private CountryEditPageCommand () {}
	
	public static CountryEditPageCommand  getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String jspPath;		
		try {
			String id = request.getParameter("id");
			Country country = CountryLogic.findByKey(id);
			request.setAttribute("country", country);
			jspPath = ConfigurationManager.getProperty("path.page.countryEditPage");
		} catch (LogicException e) {
			LOG.error(e);
			try {
				List<Country> countryList = CountryLogic.findAll();
				request.setAttribute("countryList", countryList);				
				jspPath = ConfigurationManager.getProperty("path.page.countryList");
			} catch(LogicException e1) {
				LOG.error(e1);
				jspPath = ConfigurationManager.getProperty("path.page.main");
			}
		}
		return jspPath;
	}

}