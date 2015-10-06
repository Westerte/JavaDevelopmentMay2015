
package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Resort;
import edu.nesterenko.touroperator.logic.ResortLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class DeleteResortCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteResortCommand.class);
	private static DeleteResortCommand instance = new DeleteResortCommand();
	
	private DeleteResortCommand() {}
	
	public static DeleteResortCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			ResortLogic.delete(id);		
		} catch (LogicException e) {
			LOG.error(e);
		}
		try {
			List<Resort> resortList = ResortLogic.findAll();
			request.setAttribute("resortList",  resortList);
		} catch (LogicException e) {
			LOG.error(e);
		}
		String jspPath = ConfigurationManager.getProperty("path.page.resortList");
		return jspPath;
	}

}