package edu.nesterenko.airline.entity;

import java.util.ArrayList;
import java.util.List;

public enum Manufacturer {
	BOEING(new String[]{"747", "777"}), 
	AIRBUS(new String[]{"a320", "a380"});
	
	Manufacturer(String[] models) {
		for(String model : models) {
			this.models.add(model);
		}
	}
	
	public final List<String> models = new ArrayList<String>();
}
