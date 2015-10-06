package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.RestType;
import edu.nesterenko.touroperator.logic.RestTypeLogic;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.resource.ConfigurationManager;


public class RestTypeEditPageCommand  implements Command {
	private final static Logger LOG = Logger.getLogger(RestTypeEditPageCommand .class);
	private static RestTypeEditPageCommand  instance = new RestTypeEditPageCommand ();
	
	private RestTypeEditPageCommand () {}
	
	public static RestTypeEditPageCommand  getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String jspPath;		
		try {
			String id = request.getParameter("id");
			RestType restType = RestTypeLogic.findByKey(id);
			request.setAttribute("restType", restType);
			jspPath = ConfigurationManager.getProperty("path.page.restTypeEditPage");
		} catch (LogicException e) {
			LOG.error(e);
			try {
				List<RestType> restTypeList = RestTypeLogic.findAll();
				request.setAttribute("restTypeList", restTypeList);
				jspPath = ConfigurationManager.getProperty("path.page.restTypeList");
			} catch(LogicException e1) {
				LOG.error(e1);
				jspPath = ConfigurationManager.getProperty("path.page.main");
			}
		}
		return jspPath;
	}

}