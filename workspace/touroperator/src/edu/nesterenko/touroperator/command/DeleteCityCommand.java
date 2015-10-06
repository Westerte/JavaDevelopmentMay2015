package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.logic.CityLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class DeleteCityCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteCityCommand.class);
	private static DeleteCityCommand instance = new DeleteCityCommand();
	
	private DeleteCityCommand() {}
	
	public static DeleteCityCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			CityLogic.delete(id);		
		} catch (LogicException e) {
			LOG.error(e);
		}
		try {
			List<City> cityList = CityLogic.findAll();
			request.setAttribute("cityList",  cityList);
		} catch (LogicException e) {
			LOG.error(e);
		}
		String jspPath = ConfigurationManager.getProperty("path.page.cityList");
		return jspPath;
	}

}