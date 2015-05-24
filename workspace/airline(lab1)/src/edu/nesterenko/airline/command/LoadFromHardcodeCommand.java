package edu.nesterenko.airline.command;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.dao.HardcodeDao;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.Loader;

public class LoadFromHardcodeCommand implements Command {
	private final static Logger LOG = Logger.getLogger(LoadFromHardcodeCommand.class);
	private static LoadFromHardcodeCommand instance;
	static {
		instance = new LoadFromHardcodeCommand();
	}
	public static LoadFromHardcodeCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		try {
			Loader.load(new HardcodeDao());
			response.setParameter(ResponseEnum.IS_OK, true);
		} catch (PhisicalException | LogicalException e) {
			response.setParameter(ResponseEnum.IS_OK, false);
			LOG.error(e);
		}
		return response;
	}

}
