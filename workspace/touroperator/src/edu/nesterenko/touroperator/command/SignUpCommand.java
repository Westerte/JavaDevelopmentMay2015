package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.logic.LogicException;
import edu.nesterenko.touroperator.logic.SignUpLogic;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class SignUpCommand implements Command {
	private final static Logger LOG = Logger.getLogger(SignUpCommand.class);
	private static SignUpCommand instance = new SignUpCommand();
	
	private SignUpCommand() {}
	
	public static SignUpCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String pagePath;
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repeatedPassword = request.getParameter("repeated_password");
		try {
			Client client = SignUpLogic.signUp(login, password, repeatedPassword, email);
			request.getSession(true).setAttribute("client", client);
			pagePath = ConfigurationManager.getProperty("path.page.main");
		} catch (LogicException e) {
			LOG.error(e);	
			request.setAttribute("login", login);
			request.setAttribute("email", email);
			pagePath = ConfigurationManager.getProperty("path.page.signup");					
		}		
		return pagePath;
	}

}
