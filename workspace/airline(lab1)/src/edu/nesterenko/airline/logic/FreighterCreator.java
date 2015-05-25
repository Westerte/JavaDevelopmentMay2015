package edu.nesterenko.airline.logic;

import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.entity.Freighter;
import edu.nesterenko.airline.entity.Manufacturer;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public class FreighterCreator implements Creator {

	@Override
	public Airplane createAirplane(Object[] args) throws PhisicalException, LogicalException {
		Airplane freighter = null;
		try {
			Manufacturer manufacturer = (Manufacturer) args[0];
			String model = (String) args[1];
			int maxRange = (int) args[2];
			int capacity = (int) args[3];
			int bearingCapacity = (int) args[4];
			int fuelConsumption = (int) args[5];
			int cargoHoldCount = (int)args[6];
			freighter = new Freighter(manufacturer, model, maxRange, capacity, bearingCapacity, fuelConsumption, cargoHoldCount);
		} catch (LogicalException e) {
			throw e;
		} catch (Exception e) {
			throw new PhisicalException("Bad args.", e );
		}
		
		return freighter;
	}

}
