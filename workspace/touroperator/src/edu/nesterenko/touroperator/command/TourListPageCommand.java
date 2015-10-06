package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Tour;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.ResortHotel;
import edu.nesterenko.touroperator.entity.RestType;
import edu.nesterenko.touroperator.logic.TourLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.logic.ResortHotelLogic;
import edu.nesterenko.touroperator.logic.RestTypeLogic;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class TourListPageCommand implements Command {
	
	private final static Logger LOG = Logger.getLogger(TourListPageCommand.class);
	private static TourListPageCommand instance = new TourListPageCommand();
	
	private TourListPageCommand() {}

	public static TourListPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			List<Tour> tourList = null;
			List<ResortHotel> resortHotelList = null;
			List<RestType> restTypeList = null;
			try {
				tourList = TourLogic.findAll();
				resortHotelList = ResortHotelLogic.findAll();
				restTypeList = RestTypeLogic.findAll();
			} catch (LogicException e) {
				LOG.error(e);
			}
			request.setAttribute("tourList", tourList);
			request.setAttribute("resortHotelList", resortHotelList);
			request.setAttribute("restTypeList", restTypeList);
			jspPath = ConfigurationManager.getProperty("path.page.tourList");
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		
		return jspPath;
	}

}