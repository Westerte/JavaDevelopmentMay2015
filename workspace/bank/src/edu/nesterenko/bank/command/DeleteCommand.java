package edu.nesterenko.bank.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import edu.nesterenko.bank.entity.Client;
import edu.nesterenko.bank.logic.ClientLogic;
import edu.nesterenko.bank.logic.LogicException;
import edu.nesterenko.bank.resource.ConfigurationManager;

public class DeleteCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteCommand.class);
	private static DeleteCommand instance = new DeleteCommand();
	
	private DeleteCommand() {}
	
	public static DeleteCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		String jspPath = ConfigurationManager.getProperty("path.page.main");;
		String id = request.getParameter("id");
		try {
			ClientLogic.delete(id);
			List<Client> clientList = ClientLogic.findAll();
			request.setAttribute("clientList", clientList);
		} catch (LogicException e) {
			LOG.error(e);
			request.setAttribute("status", "Logic Problem");
		}		
		return jspPath;
	}
}
