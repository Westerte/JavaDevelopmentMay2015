package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class SignUpPageCommand implements Command {
	private static SignUpPageCommand instance = new SignUpPageCommand();
	
	private SignUpPageCommand() {}
	
	public static SignUpPageCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(true);
		String pathPage;
		if(httpSession.getAttribute("client") != null) {
			pathPage = ConfigurationManager.getProperty("path.page.main");
		} else {
			pathPage = ConfigurationManager.getProperty("path.page.signup");
		}
		return pathPage;
	}

}
