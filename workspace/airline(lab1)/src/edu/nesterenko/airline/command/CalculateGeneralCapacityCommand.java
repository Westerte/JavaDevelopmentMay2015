package edu.nesterenko.airline.command;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.logic.AirlineAirplainsCalculator;

public class CalculateGeneralCapacityCommand implements Command{
	private static CalculateGeneralCapacityCommand instance;
	
	static {
		instance = new CalculateGeneralCapacityCommand();
	}
	
	private CalculateGeneralCapacityCommand() {}
	
	public static CalculateGeneralCapacityCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		int sum = AirlineAirplainsCalculator.calculateGeneralCapacity();		
		response.setParameter(ResponseEnum.SUM, sum);		
		return response;
	}
}