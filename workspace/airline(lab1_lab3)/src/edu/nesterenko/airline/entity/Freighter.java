package edu.nesterenko.airline.entity;

import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public class Freighter extends Airplane {
	private int cargoHoldCount;
	
	public Freighter(String numberPlate, Manufacturer manufacturer, String model, int maxRange, int capacity, int bearingCapacity, int fuelConsumption,
		   int cargoHoldCount) throws LogicalException, PhisicalException {
		super(numberPlate, manufacturer, model, maxRange, capacity, bearingCapacity, fuelConsumption);
		setCargoHoldCount(cargoHoldCount);
	}

	public int getCargoHoldCount() {
		return cargoHoldCount;
	}

	public void setCargoHoldCount(int cargoHoldCount) throws LogicalException {
		if (cargoHoldCount > 0) {
			this.cargoHoldCount = cargoHoldCount;
		} else {
			throw new LogicalException("cargoHoldCount must be greater then 0");
		}
	}
	
	@Override
	public String toString() {
		return String.format("numberPumber: %s manufacturer: %s model: %s maxRange: %d capacity: %d bearingCapacity: %d "
			   + "fuelConsumption: %d cargoHoldCount: %d", getNumberPlate(), getManufacturer(), getModel(), 
			   getMaxRange(), getCapacity(), getBearingCapacity(), getFuelConsumption(), cargoHoldCount);
	}
	
}
