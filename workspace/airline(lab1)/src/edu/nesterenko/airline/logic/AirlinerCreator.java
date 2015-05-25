package edu.nesterenko.airline.logic;

import edu.nesterenko.airline.entity.Airliner;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.entity.Manufacturer;
import edu.nesterenko.airline.exception.*;

public class AirlinerCreator implements Creator {

	@Override
	public Airplane createAirplane(Object[] args) throws PhisicalException, LogicalException {
		Airplane airbus = null;
		try {
			Manufacturer manufacturer = (Manufacturer) args[0];
			String model = (String) args[1];
			int maxRange = (int) args[2];
			int capacity = (int) args[3];
			int bearingCapacity = (int) args[4];
			int fuelConsumption = (int) args[5];
			int seatsCount = (int) args[6];
			int classCount = (int) args[7];
			int luggageCapacity = (int) args[8];
			airbus = new Airliner(manufacturer, model, maxRange, capacity, bearingCapacity, 
					 fuelConsumption, seatsCount, classCount, luggageCapacity);
		} catch (LogicalException e) {
			throw e;
		} catch  (ClassCastException e) {
			throw new PhisicalException("Bad args.", e );
		}
		
		return airbus;
	}

}