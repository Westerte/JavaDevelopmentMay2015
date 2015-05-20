package edu.vadimnesterenko.airline.entity;

public class Airbus extends Airplane {
	private int seatsCount;
	private int classCount;
	private int luggageCapacity;
	
	public Airbus(int capacity, int bearingCapacity, int fuelConsumption,
			      int seatsCount, int classCount, int luggageCapacity) throws BadParamException {
		super(capacity, bearingCapacity, fuelConsumption);
		setSeatsCount(seatsCount);
		setClassCount(classCount);
		setLuggageCapacity(luggageCapacity);
	}

	public int getSeatsCount() {
		return seatsCount;
	}
	
	public void setSeatsCount(int seatsCount) throws BadParamException {
		if (seatsCount > 0) {
			this.seatsCount = seatsCount;
		} else {
			throw new BadParamException("seatsCount must be greater then 0");
		}
		
	}
	
	public int getClassCount() {
		return classCount;
	}
	
	public void setClassCount(int classCount) throws BadParamException {
		if (classCount > 0) {
			this.classCount = classCount;
		} else {
			throw new BadParamException("classCount must be greater then 0");
		}
	}
	
	public int getLuggageCapacity() {
		return luggageCapacity;
	}
	
	public void setLuggageCapacity(int luggageCapacity) throws BadParamException {
		if (luggageCapacity > 0) {
			this.luggageCapacity = luggageCapacity;
		} else {
			throw new BadParamException("luggageCapacity must be greater then 0");
		}
	}
}