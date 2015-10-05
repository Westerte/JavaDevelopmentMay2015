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

public class ResortHotelListPageCommand implements Command{
	private final static Logger LOG = Logger.getLogger(ResortHotelListPageCommand.class);
	private static ResortHotelListPageCommand instance = new ResortHotelListPageCommand();
	
	private ResortHotelListPageCommand() {}

	public static ResortHotelListPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			List<Resort> resortList = null;
			List<ResortHotel> resortHotelList = null;
			try {
				resortHotelList = ResortHotelLogic.findAll();
				resortList = ResortLogic.findAll();
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
