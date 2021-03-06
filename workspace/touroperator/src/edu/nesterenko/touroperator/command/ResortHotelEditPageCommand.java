package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Resort;
import edu.nesterenko.touroperator.entity.ResortHotel;
import edu.nesterenko.touroperator.logic.ResortHotelLogic;
import edu.nesterenko.touroperator.logic.ResortLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;


public class ResortHotelEditPageCommand  implements Command {
	private final static Logger LOG = Logger.getLogger(ResortHotelEditPageCommand .class);
	private static ResortHotelEditPageCommand  instance = new ResortHotelEditPageCommand ();
	
	private ResortHotelEditPageCommand () {}
	
	public static ResortHotelEditPageCommand  getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String jspPath;		
		try {
			String id = request.getParameter("id");
			ResortHotel resortHotel = ResortHotelLogic.findByKey(id);
			request.setAttribute("id", resortHotel.getId());
			request.setAttribute("name", resortHotel.getName());
			request.setAttribute("description", resortHotel.getDescription());
			request.setAttribute("resort", resortHotel.getResort());
			request.setAttribute("stars", resortHotel.getStars());
			jspPath = ConfigurationManager.getProperty("path.page.resortHotelEditPage");
		} catch (LogicException e) {
			LOG.error(e);
			try {
				List<Resort> resortList = ResortLogic.findAll();
				List<ResortHotel> resortHotelList = ResortHotelLogic.findAll();
				request.setAttribute("resortList", resortList);
				request.setAttribute("resortHotelList", resortHotelList);
				jspPath = ConfigurationManager.getProperty("path.page.resortHotelList");
			} catch(LogicException e1) {
				LOG.error(e1);
				jspPath = ConfigurationManager.getProperty("path.page.main");
			}
		}
		return jspPath;
	}

}