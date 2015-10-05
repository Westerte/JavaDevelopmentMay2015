package edu.nesterenko.bank.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.nesterenko.bank.logic.LocaleDefiner;
import edu.nesterenko.bank.logic.LogicException;
import edu.nesterenko.bank.resource.ConfigurationManager;

public class ChangeLocaleCommand implements Command {
	private final static Logger LOG = Logger.getLogger(ChangeLocaleCommand.class);
	private static ChangeLocaleCommand instance = new ChangeLocaleCommand();
	
	private ChangeLocaleCommand() {}
	
	public static ChangeLocaleCommand getInstance() {
		return instance;
	}
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(); 
		try {
			session.setAttribute("locale", LocaleDefiner.defineLocal(request.getParameter("language")));
		} catch (LogicException e) {
			LOG.error(e);
		}
		String jspPath = (String) session.getAttribute("last_page");
		if(jspPath == null || jspPath.isEmpty()) {
			jspPath = ConfigurationManager.getProperty("path.page.index");
		}
		return jspPath;
	}
}
