package edu.nesterenko.airline.logic;

import edu.nesterenko.airline.creator.Creator;
import edu.nesterenko.airline.entity.Airline;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public class AirlineEditor {
	private AirlineEditor() {}
	
	public static void addAirplane(Creator creator, Object[] args) throws PhisicalException, LogicalException {
		Airplane airplane  = creator.createAirplane(args);
		Airline airlane = Airline.getInstance();
		airlane.setAirplanes(airplane);
	}
	
	public static void deleteAirplane(int index) throws PhisicalException {
		Airline airlane = Airline.getInstance();
		airlane.removeAirplane(index);
	}
	
	public static void clearAirlane() {
		Airline airline = Airline.getInstance();
		airline.clearAirline();
	}
}
