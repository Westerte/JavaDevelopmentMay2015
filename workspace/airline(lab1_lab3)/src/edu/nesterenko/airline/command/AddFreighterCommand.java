package edu.nesterenko.airline.command;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.RequestEnum;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.creator.FreighterCreator;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirlineEditor;

public class AddFreighterCommand implements Command {
	private final static Logger LOG = Logger.getLogger(AddFreighterCommand.class);
	private static AddFreighterCommand instance;
	
	static {
		instance = new AddFreighterCommand();
	}
	
	private AddFreighterCommand() {}
	
	public static AddFreighterCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		try {
			AirlineEditor.addAirplane(new FreighterCreator(), (Object[]) request.getParameter(RequestEnum.ARGS));
			response.setParameter(ResponseEnum.IS_OK, true);
		} catch (PhisicalException | LogicalException | ClassCastException e ) {
			response.setParameter(ResponseEnum.IS_OK, false);
			LOG.error(e);
		}
		return response;
	}

}
