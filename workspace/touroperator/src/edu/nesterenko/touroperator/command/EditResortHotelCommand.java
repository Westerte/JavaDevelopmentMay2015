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

public class EditResortHotelCommand implements Command {
	private final static Logger LOG = Logger.getLogger(EditResortHotelCommand.class);
	private static EditResortHotelCommand instance = new EditResortHotelCommand();
	
	private EditResortHotelCommand() {}
	
	public static EditResortHotelCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String stars = request.getParameter("stars");
			String resortId = request.getParameter("resort");
			try {
				ResortHotelLogic.editResortHotel(id, name, description, resortId, stars);
				List<Resort> resortList = ResortLogic.findAll();
				List<ResortHotel> resortHotelList = ResortHotelLogic.findAll();
				request.setAttribute("resortList", resortList);
				request.setAttribute("resortHotelList", resortHotelList);
				jspPath = ConfigurationManager.getProperty("path.page.resortHotelList");
			} catch (LogicException e) {
				request.setAttribute("id", id);
				request.setAttribute("name", name);
				request.setAttribute("description", description);
				request.setAttribute("stars", stars);
				request.setAttribute("resort", resortId);
				LOG.error(e);
				jspPath = ConfigurationManager.getProperty("path.page.resortHotelEditPage");
			}			
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}
}