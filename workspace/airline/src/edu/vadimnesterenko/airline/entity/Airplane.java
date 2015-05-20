package edu.vadimnesterenko.airline.entity;

public abstract class Airplane {
	private int capacity;
	private int bearingCapacity;
	private int fuelConsumption;
	
	
	
	public Airplane(int capacity, int bearingCapacity, int fuelConsumption) throws BadParamException {
		setCapacity(capacity);
		setBearingCapacity(bearingCapacity);
		setFuelConsumption(fuelConsumption);
	}

	public int getCapacity() {
		return capacity;
	}	

	public void setCapacity(int capacity) throws BadParamException{
		if (capacity > 0) {
			this.capacity = capacity; 
		} else {
			throw new BadParamException("capacity must be greater then 0");
		}		
	}
	
	public int getBearingCapacity() {
		return bearingCapacity;
	}
	
	public void setBearingCapacity(int bearingCapacity) throws BadParamException {
		if (bearingCapacity > 0) {
			this.bearingCapacity = bearingCapacity;
		} else {
			throw new BadParamException("bearingCapacity must be greater then 0");
		}	
	}

	public int getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(int fuelConsumption) throws BadParamException {
		if (fuelConsumption > 0) {
			this.fuelConsumption = fuelConsumption;
		} else {
			throw new BadParamException("fuelConsumption must be greater then 0");
		}
	}
	
}
