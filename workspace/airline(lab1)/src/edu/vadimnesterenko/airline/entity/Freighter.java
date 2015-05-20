package edu.vadimnesterenko.airline.entity;

public class Freighter extends Airplane {
	private int cargoHoldCount;
	
	public Freighter(int capacity, int bearingCapacity, int fuelConsumption,
					 int cargoHoldCount) throws BadParamException {
		super(capacity, bearingCapacity, fuelConsumption);
		setCargoHoldCount(cargoHoldCount);
	}

	public int getCargoHoldCount() {
		return cargoHoldCount;
	}

	public void setCargoHoldCount(int cargoHoldCount) throws BadParamException {
		if (cargoHoldCount > 0) {
			this.cargoHoldCount = cargoHoldCount;
		} else {
			throw new BadParamException("cargoHoldCount must be greater then 0");
		}
	}
	
}