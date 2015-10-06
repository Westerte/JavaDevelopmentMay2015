
package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Tour;
import edu.nesterenko.touroperator.logic.TourLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class DeleteTourCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteTourCommand.class);
	private static DeleteTourCommand instance = new DeleteTourCommand();
	
	private DeleteTourCommand() {}
	
	public static DeleteTourCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			TourLogic.delete(id);		
		} catch (LogicException e) {
			LOG.error(e);
		}
		try {
			List<Tour> tourList = TourLogic.findAll();
			request.setAttribute("tourList",  tourList);
		} catch (LogicException e) {
			LOG.error(e);
		}
		String jspPath = ConfigurationManager.getProperty("path.page.tourList");
		return jspPath;
	}

}