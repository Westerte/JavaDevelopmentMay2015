package edu.nesterenko.airline.command;

import java.util.List;





import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.bean.ResponseEnum;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.logic.AirlineAirplainsFinder;

public class FindAllCommand implements Command {
	private static FindAllCommand instance;
	
	static {
		instance = new  FindAllCommand();
	}
	
	private FindAllCommand() {}
	
	public static  FindAllCommand getInstance() {
		return instance;
	}
	
	@Override
	public Response processRequest(Request request) {
		Response response = new Response();
		List<Airplane> airplanes = AirlineAirplainsFinder.findAll();
		response.setParameter(ResponseEnum.AIRPLANES_LIST, airplanes);		
		return response;
	}
}
