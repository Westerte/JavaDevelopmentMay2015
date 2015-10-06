package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.logic.CityLogic;
import edu.nesterenko.touroperator.logic.CountryLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;


public class CityEditPageCommand  implements Command {
	private final static Logger LOG = Logger.getLogger(CityEditPageCommand .class);
	private static CityEditPageCommand  instance = new CityEditPageCommand ();
	
	private CityEditPageCommand () {}
	
	public static CityEditPageCommand  getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String jspPath;		
		try {
			String id = request.getParameter("id");
			City city = CityLogic.findByKey(id);
			request.setAttribute("id", city.getId());
			request.setAttribute("name", city.getName());
			request.setAttribute("description", city.getDescription());
			request.setAttribute("country", city.getCountry());
			jspPath = ConfigurationManager.getProperty("path.page.cityEditPage");
		} catch (LogicException e) {
			LOG.error(e);
			try {
				List<City> cityList = CityLogic.findAll();
				List<Country> countryList = CountryLogic.findAll();
				request.setAttribute("cityList", cityList);
				request.setAttribute("countryList", countryList);
				jspPath = ConfigurationManager.getProperty("path.page.cityList");
			} catch(LogicException e1) {
				LOG.error(e1);
				jspPath = ConfigurationManager.getProperty("path.page.main");
			}
		}
		return jspPath;
	}

}