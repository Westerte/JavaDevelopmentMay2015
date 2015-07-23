package edu.nesterenko.touroperator.command;

import javax.servlet.http.HttpServletRequest;

import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class LogoutCommand implements Command {
	private static LogoutCommand instance = new LogoutCommand();
	
	private LogoutCommand() {}
	
	public static LogoutCommand getInstance() {
		return instance;
	}

	@Override
	public String execute(HttpServletRequest request) {
		request.getSession().invalidate();		
		String pagePath = ConfigurationManager.getProperty("path.page.login");
		return pagePath;
	}
}
