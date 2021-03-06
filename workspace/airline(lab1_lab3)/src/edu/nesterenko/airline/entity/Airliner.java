package edu.nesterenko.airline.entity;

import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public class Airliner extends Airplane {
	private int seatsCount;
	private int classCount;
	private int luggageCapacity;
	
	public Airliner(String numberPlate, Manufacturer manufacturer, String model, int maxRange, int capacity, int bearingCapacity, int fuelConsumption,
			        int seatsCount, int classCount, int luggageCapacity) throws LogicalException, PhisicalException {
		super(numberPlate, manufacturer, model, maxRange, capacity, bearingCapacity, fuelConsumption);
		setSeatsCount(seatsCount);
		setClassCount(classCount);
		setLuggageCapacity(luggageCapacity);
	}

	public int getSeatsCount() {
		return seatsCount;
	}
	
	public void setSeatsCount(int seatsCount) throws LogicalException {
		if (seatsCount > 0) {
			this.seatsCount = seatsCount;
		} else {
			throw new LogicalException("seatsCount must be greater then 0");
		}		
	}
	
	public int getClassCount() {
		return classCount;
	}
	
	public void setClassCount(int classCount) throws LogicalException {
		if (classCount > 0) {
			this.classCount = classCount;
		} else {
			throw new LogicalException("classCount must be greater then 0");
		}
	}
	
	public int getLuggageCapacity() {
		return luggageCapacity;
	}
	
	public void setLuggageCapacity(int luggageCapacity) throws LogicalException {
		if (luggageCapacity > 0) {
			this.luggageCapacity = luggageCapacity;
		} else {
			throw new LogicalException("luggageCapacity must be greater then 0");
		}
	}
	
	@Override
	public String toString() {
		return String.format("numberPlate: %s manufacturer: %s model: %s maxRange: %d capacity: %d bearingCapacity: %d "
			   + "fuelConsumption: %d seatsCount: %d classCount: %d luggageCapacity: %d", getNumberPlate(),
			   getManufacturer(), getModel(), getMaxRange(), getCapacity(), getBearingCapacity(), getFuelConsumption(), seatsCount,
			   classCount, luggageCapacity);
	}
}
