
package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.logic.CountryLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class DeleteCountryCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteCountryCommand.class);
	private static DeleteCountryCommand instance = new DeleteCountryCommand();
	
	private DeleteCountryCommand() {}
	
	public static DeleteCountryCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			CountryLogic.delete(id);		
		} catch (LogicException e) {
			LOG.error(e);
		}
		try {
			List<Country> countryList = CountryLogic.findAll();
			request.setAttribute("countryList",  countryList);
		} catch (LogicException e) {
			LOG.error(e);
		}
		String jspPath = ConfigurationManager.getProperty("path.page.countryList");
		return jspPath;
	}

}