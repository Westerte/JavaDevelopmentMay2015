package edu.nesterenko.touroperator.command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class ProfilePageCommand implements Command {

	private final static Logger LOG = Logger.getLogger(ProfilePageCommand.class);
	private static ProfilePageCommand instance = new ProfilePageCommand();
	
	private ProfilePageCommand() {}
	
	public static ProfilePageCommand getInstance() {
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		Client client = (Client)session.getAttribute("client");
		String jspPath;
		if(client != null) {
			jspPath = ConfigurationManager.getProperty("path.page.profile");
		} else {
			LOG.error("Client null");
			jspPath = ConfigurationManager.getProperty("path.page.main");
		}
		return jspPath;
	}
}
