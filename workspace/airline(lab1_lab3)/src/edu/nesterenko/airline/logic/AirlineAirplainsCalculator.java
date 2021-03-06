package edu.nesterenko.airline.logic;

import java.util.List;

import edu.nesterenko.airline.entity.Airline;
import edu.nesterenko.airline.entity.Airplane;

public class AirlineAirplainsCalculator {
	private AirlineAirplainsCalculator() {}
	
	public static int calculateGeneralCapacity() {
		List<Airplane> airplanes = Airline.getInstance().getAirplanes();
		int sum = 0;
		for (Airplane airplane: airplanes) {
			sum += airplane.getCapacity();
		}
		return sum;
	}
	
	public static int calculateGeneralBearingCapacity() {
		List<Airplane> airplanes = Airline.getInstance().getAirplanes();
		int sum = 0;
		for (Airplane airplane: airplanes) {
			sum += airplane.getBearingCapacity();
		}
		return sum;
	}
}
