package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class LoginPageCommand implements Command {
	private static LoginPageCommand instance = new LoginPageCommand();
	
	private LoginPageCommand() {}
	
	public static LoginPageCommand getInstance() {
		return instance;
	}
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(true);
		String pathPage;
		if(httpSession.getAttribute("client") != null) {
			pathPage = ConfigurationManager.getProperty("path.page.main");
		} else {
			pathPage = ConfigurationManager.getProperty("path.page.login");
		}
		return pathPage;
	}
}
