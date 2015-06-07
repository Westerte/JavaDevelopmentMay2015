package edu.nesterenko.airline.command;

import java.util.List;

import org.apache.log4j.Logger;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.RequestEnum;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.logic.AirlineAirplainsFinder;

public class FindByFuelConsumptionCommand implements Command {
	private final static Logger LOG = Logger.getLogger(FindByFuelConsumptionCommand.class);
	private static FindByFuelConsumptionCommand instance;
	
	static {
		instance = new FindByFuelConsumptionCommand();
	}
	
	private FindByFuelConsumptionCommand() {}
	
	public static FindByFuelConsumptionCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		try {			
			int minRange = (int)request.getParameter(RequestEnum.MIN_RANGE);
			int maxRange = (int)request.getParameter(RequestEnum.MAX_RANGE);
			List<Airplane> airplanes = AirlineAirplainsFinder.findByFuelConsumption(minRange, maxRange);
			response.setParameter(ResponseEnum.AIRPLANES_LIST, airplanes);			
		} catch (LogicalException | ClassCastException e) {
			response.setParameter(ResponseEnum.IS_OK, false);
			LOG.error(e);
		}
		return response;
	}
}
