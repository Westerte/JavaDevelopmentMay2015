package edu.nesterenko.airline.creator;

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
			String numberPlate = (String) args[0];
			Manufacturer manufacturer = (Manufacturer) args[1];
			String model = (String) args[2];
			int maxRange = (int) args[3];
			int capacity = (int) args[4];
			int bearingCapacity = (int) args[5];
			int fuelConsumption = (int) args[6];
			int cargoHoldCount = (int)args[7];
			freighter = new Freighter(numberPlate, manufacturer, model, maxRange, 
					capacity, bearingCapacity, fuelConsumption, cargoHoldCount);
		} catch (LogicalException e) {
			throw e;
		} catch (Exception e) {
			throw new PhisicalException("Bad args.", e );
		}
		
		return freighter;
	}

}
