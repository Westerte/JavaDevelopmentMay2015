package edu.nesterenko.airline.entity;

import edu.nesterenko.airline.exception.LogicalException;

public class Airbus extends Airplane {
	private int seatsCount;
	private int classCount;
	private int luggageCapacity;
	
	public Airbus(Model model ,int maxRange, int capacity, int bearingCapacity, int fuelConsumption,
			      int seatsCount, int classCount, int luggageCapacity) throws LogicalException {
		super(model, maxRange, capacity, bearingCapacity, fuelConsumption);
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
		return "model: " + super.getModel() +" maxRange: " + super.getMaxRange() + " capacity: " + super.getCapacity() + " bearingCapacity: " + super.getBearingCapacity() +
				" fuelConsumption: " + super.getFuelConsumption() + " seatsCount: "+ seatsCount + " classCount: " + 
				classCount + " luggageCapacity: " + luggageCapacity;		
	}
}
