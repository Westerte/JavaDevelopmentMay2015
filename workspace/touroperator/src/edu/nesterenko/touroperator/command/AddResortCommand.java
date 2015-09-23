package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.dao.CityDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.dao.ResortDao;
import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.Resort;
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
			int cityId = Integer.parseInt(request.getParameter("city"));
			try {
				ResortLogic.addResort(name, description, cityId);
			} catch (LogicException e) {
				LOG.error(e);
			}
			ResortDao resortDao = new ResortDao();
			List<Resort> resortList = null;
			try {
				resortList = resortDao.findAll();
			} catch (DaoException e) {
				LOG.error(e);
			}
			CityDao cityDao = new CityDao();
			List<City> cityList = null;
			try {
				cityList = cityDao.findAll();
			} catch (DaoException e) {
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
