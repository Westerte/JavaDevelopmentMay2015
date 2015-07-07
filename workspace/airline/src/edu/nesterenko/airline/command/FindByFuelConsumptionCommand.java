package edu.nesterenko.airline.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

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
	public String execute(HttpServletRequest request) {
		try {			
			String min = request.getParameter("min");
			String max = request.getParameter("max");
			try {
				int minRange = Integer.parseInt(min);
				int maxRange = Integer.parseInt(max);		
				List<Airplane> airplanes = AirlineAirplainsFinder.findByFuelConsumption(minRange, maxRange);
				request.setAttribute("airplanes", airplanes);	
			} catch(NumberFormatException e) {}					
		} catch (LogicalException | ClassCastException e) {
			LOG.error(e);
		}
		return "jsp/airplains.jsp";
	}
}
