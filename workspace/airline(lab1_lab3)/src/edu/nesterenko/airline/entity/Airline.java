package edu.nesterenko.airline.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nesterenko.airline.exception.PhisicalException;

public class Airline {
	private static Airline instance;
	private List<Airplane> airplanes = new ArrayList<Airplane>();
	
	static {
		instance = new Airline();
	}
	
	private Airline() {}
	
	public static Airline getInstance() {
		return instance; 
	}
	
	public Airplane getAirplanes(int index) throws PhisicalException {
		try {
			return airplanes.get(index);		
		} catch (IndexOutOfBoundsException e) {
			throw new PhisicalException(e);
		}
	}
	
	public void setAirplanes(Airplane airplane) throws PhisicalException {
		if(null != airplane) {
			this.airplanes.add(airplane);
		} else {
			throw new PhisicalException("airplane must be not null");
		}
	}

	public List<Airplane> getAirplanes() {
		return Collections.unmodifiableList(airplanes);
	}

	public void setAirplanes(List<Airplane> airplanes) {
		this.airplanes = new ArrayList<Airplane>(airplanes);
	}

	public Airplane removeAirplane(int index) throws PhisicalException {
		try {
			return airplanes.remove(index);		
		} catch (IndexOutOfBoundsException e) {
			throw new PhisicalException(e);
		}		
	}	
}
