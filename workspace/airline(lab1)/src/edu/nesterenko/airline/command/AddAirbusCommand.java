package edu.nesterenko.airline.command;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.RequestEnum;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirbusCreator;
import edu.nesterenko.airline.logic.AirlineEditor;

public class AddAirbusCommand implements Command {
	private final static Logger LOG = Logger.getLogger(AddAirbusCommand.class);
	private static AddAirbusCommand instance;
	
	static {
		instance = new AddAirbusCommand();
	}
	private AddAirbusCommand(){	}
	
	public static AddAirbusCommand getInstance() {
		return instance;
	}
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		try {
			try {
				AirlineEditor.addAirplane(new AirbusCreator(), (Object[]) request.getParameter(RequestEnum.ARGS));
				response.setParameter(ResponseEnum.IS_OK, true);
			} catch (ClassCastException e) {
				throw new PhisicalException("Bad args.");
			}
		} catch (PhisicalException | LogicalException e) {
			response.setParameter(ResponseEnum.IS_OK, false);
			LOG.error(e);
		}
		return response;
	}

}
