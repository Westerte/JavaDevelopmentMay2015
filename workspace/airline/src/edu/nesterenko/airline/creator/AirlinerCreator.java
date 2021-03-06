package edu.nesterenko.airline.creator;

import edu.nesterenko.airline.entity.Airliner;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.entity.Manufacturer;
import edu.nesterenko.airline.exception.*;

public class AirlinerCreator implements Creator {

	@Override
	public Airplane createAirplane(Object[] args) throws PhisicalException, LogicalException {
		Airplane airbus = null;
		try {
			String numberPlate = (String) args[0];
			Manufacturer manufacturer = (Manufacturer) args[1];
			String model = (String) args[2];
			int maxRange = (int) args[3];
			int capacity = (int) args[4];
			int bearingCapacity = (int) args[5];
			int fuelConsumption = (int) args[6];
			int seatsCount = (int) args[7];
			int classCount = (int) args[8];
			int luggageCapacity = (int) args[9];
			airbus = new Airliner(numberPlate, manufacturer, model, maxRange, capacity, bearingCapacity, 
					 fuelConsumption, seatsCount, classCount, luggageCapacity);
		} catch (LogicalException e) {
			throw e;
		} catch  (ClassCastException e) {
			throw new PhisicalException("Bad args.", e );
		}
		
		return airbus;
	}

}
