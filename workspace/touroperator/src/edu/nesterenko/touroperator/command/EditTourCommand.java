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

public class EditTourCommand implements Command {
	private final static Logger LOG = Logger.getLogger(EditTourCommand.class);
	private static EditTourCommand instance = new EditTourCommand();
	
	private EditTourCommand() {}
	
	public static EditTourCommand getInstance() {
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
			String cost = request.getParameter("cost");
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			String food = request.getParameter("food");
			String path = request.getParameter("path");
			String pathTime = request.getParameter("pathTime");
			String restType = request.getParameter("restType");
			String resortHotel = request.getParameter("resortHotel");
			try {
				TourLogic.editTour(id, name, description, cost,
						 beginDate, endDate, food, path, 
						 pathTime, restType, resortHotel);
				List<Tour> tourList = TourLogic.findAll();
				List<ResortHotel> resortHotelList = ResortHotelLogic.findAll();
				List<RestType> restTypeList = RestTypeLogic.findAll();
				request.setAttribute("tourList", tourList);
				request.setAttribute("resortHotelList", resortHotelList);
				request.setAttribute("restTypeList", restTypeList);
				jspPath = ConfigurationManager.getProperty("path.page.tourList");
			} catch (LogicException e) {
				request.setAttribute("id", id);
				request.setAttribute("name", name );
				request.setAttribute("description", description );
				request.setAttribute("cost", cost );
				request.setAttribute("beginDate", beginDate );
				request.setAttribute("endDate", endDate );
				request.setAttribute("food", food );
				request.setAttribute("path", path );
				request.setAttribute("pathTime", pathTime );
				request.setAttribute("restType", restType );
				request.setAttribute("resortHotel", resortHotel );
				LOG.error(e);
				jspPath = ConfigurationManager.getProperty("path.page.tourEditPage");			}			
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}

}
