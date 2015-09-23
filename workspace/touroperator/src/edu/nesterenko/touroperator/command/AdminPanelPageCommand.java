package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class AdminPanelPageCommand implements Command {
	private final static Logger LOG = Logger.getLogger(AdminPanelPageCommand.class);
	private static AdminPanelPageCommand instance = new AdminPanelPageCommand();
	
	private AdminPanelPageCommand() {}
	
	public static AdminPanelPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null && client.getClientType() == ClientType.ADMIN) {
			jspPath = ConfigurationManager.getProperty("path.page.adminPanel");
		} else {
			LOG.error("Client null");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}
}
