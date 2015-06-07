package edu.nesterenko.airline.command;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.logic.AirlineAirplainsCalculator;

public class CalculateGeneralBearingCapacityCommand  implements Command {
	private static CalculateGeneralBearingCapacityCommand instance;
	
	static {
		instance = new CalculateGeneralBearingCapacityCommand();
	}

	private CalculateGeneralBearingCapacityCommand() {}
	
	public static CalculateGeneralBearingCapacityCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		int sum = AirlineAirplainsCalculator.calculateGeneralBearingCapacity();		
		response.setParameter(ResponseEnum.SUM, sum);		
		return response;
	}
}
