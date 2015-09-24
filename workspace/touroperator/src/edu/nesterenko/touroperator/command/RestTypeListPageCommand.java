package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.dao.RestTypeDao;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.RestType;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class RestTypeListPageCommand implements Command {
	private final static Logger LOG = Logger.getLogger(RestTypeListPageCommand.class);
	private static RestTypeListPageCommand instance = new RestTypeListPageCommand();
	
	private RestTypeListPageCommand() {}

	public static RestTypeListPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			RestTypeDao restTypeDao= new RestTypeDao();
			List<RestType> restTypeList = null;
			try {
				restTypeList = restTypeDao.findAll();
			} catch (DaoException e) {
				LOG.error(e);
			}
			request.setAttribute("restTypeList", restTypeList);
			jspPath = ConfigurationManager.getProperty("path.page.restTypeList");
		} else {
			LOG.error("Client null or no Admin");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		
		return jspPath;
	}
}
