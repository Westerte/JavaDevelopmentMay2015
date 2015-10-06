package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.Resort;
import edu.nesterenko.touroperator.entity.ResortHotel;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.logic.ResortHotelLogic;
import edu.nesterenko.touroperator.logic.ResortLogic;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class AddResortHotelCommand implements Command {
	private final static Logger LOG = Logger.getLogger(AddResortHotelCommand.class);
	private static AddResortHotelCommand instance = new AddResortHotelCommand();
	
	private AddResortHotelCommand() {}
	
	public static AddResortHotelCommand getInstance() {
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
			String stars = request.getParameter("stars");
			String resortId = request.getParameter("resort");
			try {
				ResortHotelLogic.addResortHotel(name, description, resortId, stars);
			} catch (LogicException e) {
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				request.setAttribute("stars", stars);
				request.setAttribute("resort", resortId);
				LOG.error(e);
			}

			List<Resort> resortList = null;
			List<ResortHotel> resortHotelList = null;
			try {
				resortList = ResortLogic.findAll();
				resortHotelList = ResortHotelLogic.findAll();
			} catch (LogicException e) {
				LOG.error(e);
			}
			request.setAttribute("resortList", resortList);
			request.setAttribute("resortHotelList", resortHotelList);
			jspPath = ConfigurationManager.getProperty("path.page.resortHotelList");
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}
}