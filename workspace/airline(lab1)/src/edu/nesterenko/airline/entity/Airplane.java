package edu.nesterenko.airline.entity;

import edu.nesterenko.airline.exception.LogicalException;

public abstract class Airplane {
	private Model model;
	private int maxRange;
	private int capacity;
	private int bearingCapacity;
	private int fuelConsumption;	
	
	
	public Airplane(Model model, int maxRange, int capacity, int bearingCapacity, int fuelConsumption) throws LogicalException {
		setModel(model);
		setMaxRange(maxRange);
		setCapacity(capacity);
		setBearingCapacity(bearingCapacity);
		setFuelConsumption(fuelConsumption);
	}

	public int getCapacity() {
		return capacity;
	}	

	public void setCapacity(int capacity) throws LogicalException{
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}