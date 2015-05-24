package edu.vadimnesterenko.airline.entity;

import java.util.ArrayList;
import java.util.List;

public class Airline {
	private List<Airplane> airplanes = new ArrayList<Airplane>();
	
	public Airplane getAirplanes(int index) throws BadParamException {
		try {
			return airplanes.get(index);		
		} catch (IndexOutOfBoundsException ex) {
			throw new BadParamException(ex);
		}
	}
	
	public void setAirplanes(Airplane airplane) {
		this.airplanes.add(airplane);
	}

	public List<Airplane> getAirplanes() {
		return airplanes;
	}

	public void setAirplanes(List<Airplane> airplanes) {
		this.airplanes = airplanes;
	}	
}
