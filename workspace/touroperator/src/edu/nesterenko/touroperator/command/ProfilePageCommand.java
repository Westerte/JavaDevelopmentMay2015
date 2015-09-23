package edu.nesterenko.touroperator.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.dao.CountryDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.entity.ClientType;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.logic.LoginLogic;
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
