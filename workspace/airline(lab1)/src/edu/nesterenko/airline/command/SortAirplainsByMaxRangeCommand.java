package edu.nesterenko.airline.command;

import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.logic.AirlineAirplainsSorter;

public class SortAirplainsByMaxRangeCommand implements Command {
	private static SortAirplainsByMaxRangeCommand instance;	
	
	static {
		instance = new SortAirplainsByMaxRangeCommand();
	}
	
	private SortAirplainsByMaxRangeCommand() {}
	
	public static SortAirplainsByMaxRangeCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		AirlineAirplainsSorter.sortAirplanesByMaxRange();
		response.setParameter(ResponseEnum.IS_OK, true);		
		return response;
	}
}
