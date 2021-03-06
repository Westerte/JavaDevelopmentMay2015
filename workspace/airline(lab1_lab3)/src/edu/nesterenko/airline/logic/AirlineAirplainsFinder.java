package edu.nesterenko.airline.logic;

import java.util.ArrayList;
import java.util.List;

import edu.nesterenko.airline.entity.Airline;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.exception.LogicalException;

public class AirlineAirplainsFinder {
	
	public static List<Airplane> findByFuelConsumption(int minRange, 
				  int maxRange) throws LogicalException {
		if (minRange < 0 || maxRange < 0) {
			throw new LogicalException("minRange and maxRange must be greater then 0");
		}
		if (minRange > maxRange) {
			throw new LogicalException("maxRange must be greater then minRange");
		}
		List<Airplane> airplanes = Airline.getInstance().getAirplanes();
		List<Airplane> result = new ArrayList<Airplane>();
		for (Airplane airplane : airplanes) {
			if (airplane.getFuelConsumption() > minRange  && 
				airplane.getFuelConsumption() < maxRange) {
				result.add(airplane);
			}
		}
		return result;
	}
	
	public static List<Airplane> findAll() {
		List<Airplane> airplanes = Airline.getInstance().getAirplanes();
		return airplanes;
	}
}
