package edu.nesterenko.airline.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
	public String execute(HttpServletRequest request) {
		List<Airplane> airplanes = AirlineAirplainsFinder.findAll();
		request.setAttribute("airplanes", airplanes);	
		return "jsp/airplains.jsp";
	}
}
