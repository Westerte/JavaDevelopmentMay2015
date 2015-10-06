
package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.ResortHotel;
import edu.nesterenko.touroperator.logic.ResortHotelLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class DeleteResortHotelCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteResortHotelCommand.class);
	private static DeleteResortHotelCommand instance = new DeleteResortHotelCommand();
	
	private DeleteResortHotelCommand() {}
	
	public static DeleteResortHotelCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			ResortHotelLogic.delete(id);		
		} catch (LogicException e) {
			LOG.error(e);
		}
		try {
			List<ResortHotel> resortHotelList = ResortHotelLogic.findAll();
			request.setAttribute("resortHotelList",  resortHotelList);
		} catch (LogicException e) {
			LOG.error(e);
		}
		String jspPath = ConfigurationManager.getProperty("path.page.resortHotelList");
		return jspPath;
	}

}