package edu.nesterenko.airline.entity;

import java.util.regex.Pattern;

import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public abstract class Airplane {
	private String numberPlate;
	private Manufacturer manufacturer;
	private String model;
	private int maxRange;
	private int capacity;
	private int bearingCapacity;
	private int fuelConsumption;	
	
	
	public Airplane(String numberPlate, Manufacturer manufacturer, String model, int maxRange,
		   int capacity, int bearingCapacity, int fuelConsumption) throws LogicalException, PhisicalException {
		setNumberPlate(numberPlate);
		setManufacturer(manufacturer);
		setModel(model);
		setMaxRange(maxRange);
		setCapacity(capacity);
		setBearingCapacity(bearingCapacity);
		setFuelConsumption(fuelConsumption);
	}

	public int getCapacity() {
		return capacity;
	}	

	public void setCapacity(int capacity) throws LogicalException {
		if (capacity > 0) {
			this.capacity = capacity; 
		} else {
			throw new LogicalException("capacity must be greater then 0");
		}		
	}
	
	public int getBearingCapacity() {
		return bearingCapacity;
	}
	
	public void setBearingCapacity(int bearingCapacity) throws LogicalException {
		if (bearingCapacity > 0) {
			this.bearingCapacity = bearingCapacity;
		} else {
			throw new LogicalException("bearingCapacity must be greater then 0");
		}	
	}

	public int getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(int fuelConsumption) throws LogicalException {
		if (fuelConsumption > 0) {
			this.fuelConsumption = fuelConsumption;
		} else {
			throw new LogicalException("fuelConsumption must be greater then 0");
		}
	}

	public int getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(int maxRange) throws LogicalException {
		if (maxRange > 0) {
			this.maxRange = maxRange;
		} else {
			throw new LogicalException("maxRange must be greater then 0");
		}
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) throws LogicalException {
		if(null != manufacturer) {
			this.manufacturer = manufacturer;
			this.model = "";
		} else {
			throw new LogicalException("manufacturer must be not null");
		}
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) throws LogicalException {
		if(manufacturer.getModels().contains(model)) {
			this.model = model;
		} else {
			throw new LogicalException("manufacturer doesn't have this model");
		}
	}

	public String getNumberPlate() {		
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) throws LogicalException {
		if(Pattern.matches("EW-[\\d]{3}(?:(?:PA)|(?:PO)|(?:PJ))", numberPlate)) {
			this.numberPlate = numberPlate;
		} else {
			throw new LogicalException("numberPlate has invalid format");
		}
	}

	
}
