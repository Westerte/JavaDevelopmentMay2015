package edu.nesterenko.airline.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Manufacturer {
	BOEING(new String[]{"747", "777"}), 
	AIRBUS(new String[]{"a320", "a380"});
	
	private List<String> models = new ArrayList<String>();
	
	Manufacturer(String[] models) {
		for(String model : models) {
			this.models.add(model);
		}
	}	
	
	public List<String> getModels() {
		return Collections.unmodifiableList(models);
	}
}
