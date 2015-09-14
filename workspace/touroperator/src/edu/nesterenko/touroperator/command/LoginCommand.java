package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.logic.LoginLogic;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class LoginCommand implements Command {
	private final static Logger LOG = Logger.getLogger(LoginCommand.class);
	private static LoginCommand instance = new LoginCommand();
	
	private LoginCommand() {}
	
	public static LoginCommand getInstance() {
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(true);
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String pagePath;
		try {
			if(httpSession.getAttribute("client") == null) {
				Client client = LoginLogic.checkClient(login, password);
				httpSession.setAttribute("client", client);		
			}
			pagePath = ConfigurationManager.getProperty("path.page.main");
		} catch (LogicException e) {
			LOG.error(e);
			request.setAttribute("login", login);
			request.setAttribute("status", true);
			pagePath = ConfigurationManager.getProperty("path.page.login");
		}
		return pagePath;
	}
}
