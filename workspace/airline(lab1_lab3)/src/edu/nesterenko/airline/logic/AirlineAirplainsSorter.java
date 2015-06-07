package edu.nesterenko.airline.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nesterenko.airline.entity.Airline;
import edu.nesterenko.airline.entity.Airplane;

public class AirlineAirplainsSorter {
	private AirlineAirplainsSorter() {}
	
	public static void sortAirplanesByMaxRange() {
		Airline airline = Airline.getInstance();
		List<Airplane> airplanes = airline.getAirplanes();
		List<Airplane> airplanesClone =  new ArrayList<Airplane>(airplanes);
		Collections.sort(airplanesClone , new MaxRangeComparator());
		airline.setAirplanes(airplanesClone);
	}
}
