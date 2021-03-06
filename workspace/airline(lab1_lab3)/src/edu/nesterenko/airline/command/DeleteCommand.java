package edu.nesterenko.airline.command;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.RequestEnum;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirlineEditor;

public class DeleteCommand implements Command {
	private final static Logger LOG = Logger.getLogger(DeleteCommand.class);
	private static DeleteCommand instance;
	
	static {
		instance = new DeleteCommand();
	}
	
	private DeleteCommand() {}
	
	public static DeleteCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		try {
			int index = (int)request.getParameter(RequestEnum.INDEX);
			AirlineEditor.deleteAirplane(index);
			response.setParameter(ResponseEnum.IS_OK, true);
		} catch (PhisicalException | ClassCastException e) {
			response.setParameter(ResponseEnum.IS_OK, false);
			LOG.error(e);
		}
		return response;
	}
}
