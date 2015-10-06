package edu.nesterenko.bank.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.bank.entity.Client;
import edu.nesterenko.bank.logic.ClientLogic;
import edu.nesterenko.bank.logic.LogicException;
import edu.nesterenko.bank.resource.ConfigurationManager;

public class ViewAllCommand implements Command {
	private final static Logger LOG = Logger.getLogger(ViewAllCommand.class);
	private static ViewAllCommand instance = new ViewAllCommand();
	
	private ViewAllCommand() {}
	
	public static ViewAllCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		try {
			List<Client> clientList = ClientLogic.findAll();
			request.setAttribute("clientList", clientList);
		} catch (LogicException e) {
			LOG.error(e);
			request.setAttribute("status", "faild");
		}
		String jspPath = ConfigurationManager.getProperty("path.page.main");
		return jspPath;
	}
}