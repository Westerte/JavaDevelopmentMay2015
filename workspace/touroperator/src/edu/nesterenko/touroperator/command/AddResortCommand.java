package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.Resort;
import edu.nesterenko.touroperator.logic.CityLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.logic.ResortLogic;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class AddResortCommand implements Command {
	private final static Logger LOG = Logger.getLogger(AddResortCommand.class);
	private static AddResortCommand instance = new AddResortCommand();
	
	private AddResortCommand() {}
	
	public static AddResortCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String cityId = request.getParameter("city");
			try {
				ResortLogic.addResort(name, description, cityId);
			} catch (LogicException e) {
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				request.setAttribute("city", cityId);
				LOG.error(e);
			}
			List<Resort> resortList = null;
			List<City> cityList = null;
			try {
				resortList = ResortLogic.findAll();
				cityList = CityLogic.findAll();
			} catch (LogicException e) {
				LOG.error(e);
			}
			request.setAttribute("resortList", resortList);
			request.setAttribute("cityList", cityList);
			jspPath = ConfigurationManager.getProperty("path.page.resortList");
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}
}