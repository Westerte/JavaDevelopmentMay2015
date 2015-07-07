package edu.nesterenko.airline.command;

import javax.servlet.http.HttpServletRequest;

import edu.nesterenko.airline.logic.AirlineAirplainsFinder;
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
	public String execute(HttpServletRequest request) {
		AirlineAirplainsSorter.sortAirplanesByMaxRange();
		request.setAttribute("airplanes", AirlineAirplainsFinder.findAll());
		return "jsp/airplains.jsp";
	}
}
