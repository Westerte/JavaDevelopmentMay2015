package edu.nesterenko.airline.entity;

import edu.nesterenko.airline.exception.LogicalException;

public class Freighter extends Airplane {
	private int cargoHoldCount;
	
	public Freighter(Model model, int maxRange, int capacity, int bearingCapacity, int fuelConsumption,
					 int cargoHoldCount) throws LogicalException {
		super(model, maxRange, capacity, bearingCapacity, fuelConsumption);
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
		return "model: " + super.getModel() +" maxRange: " + super.getMaxRange() + " capacity: " + super.getCapacity() + " bearingCapacity: " + super.getBearingCapacity() +
				" fuelConsumption: " + super.getFuelConsumption() + " cargoHoldCount: " + cargoHoldCount;	
	}
	
}