
package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.RestType;
import edu.nesterenko.touroperator.logic.RestTypeLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class DeleteRestTypeCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteRestTypeCommand.class);
	private static DeleteRestTypeCommand instance = new DeleteRestTypeCommand();
	
	private DeleteRestTypeCommand() {}
	
	public static DeleteRestTypeCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			RestTypeLogic.delete(id);		
		} catch (LogicException e) {
			LOG.error(e);
		}
		try {
			List<RestType> restTypeList = RestTypeLogic.findAll();
			request.setAttribute("restTypeList",  restTypeList);
		} catch (LogicException e) {
			LOG.error(e);
		}
		String jspPath = ConfigurationManager.getProperty("path.page.restTypeList");
		return jspPath;
	}

}