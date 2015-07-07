package edu.nesterenko.airline.command;

import javax.servlet.http.HttpServletRequest;

import edu.nesterenko.airline.logic.AirlineAirplainsCalculator;
import edu.nesterenko.airline.logic.AirlineAirplainsFinder;

public class CalculateGeneralCapacityCommand implements Command {
	private static CalculateGeneralCapacityCommand instance;
	
	static {
		instance = new CalculateGeneralCapacityCommand();
	}
	
	private CalculateGeneralCapacityCommand() {}
	
	public static CalculateGeneralCapacityCommand getInstance() {
		return instance;
	}
	
	@Override
	public String execute(HttpServletRequest request) {
		int sum = AirlineAirplainsCalculator.calculateGeneralCapacity();		
		request.setAttribute("generalCapacity", sum);
		request.setAttribute("airplanes", AirlineAirplainsFinder.findAll());
		return "jsp/airplains.jsp";
	}
}