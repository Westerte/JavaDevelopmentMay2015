package edu.nesterenko.airline.logic;

import edu.nesterenko.airline.entity.Airbus;
import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.entity.Model;
import edu.nesterenko.airline.exception.*;

public class AirbusCreator implements Creator {

	@Override
	public Airplane createAirplane(Object[] args) throws PhisicalException, LogicalException{
		Airplane airbus = null;
		try {
			Model model = (Model) args[0]; 
			int maxRange = (int) args[1];
			int capacity = (int) args[2];
			int bearingCapacity = (int) args[3];
			int fuelConsumption = (int) args[4];
			int seatsCount = (int) args[5];
			int classCount = (int) args[6];
			int luggageCapacity = (int) args[7];
			airbus = new Airbus(model, maxRange, capacity, bearingCapacity, fuelConsumption, 
					seatsCount, classCount, luggageCapacity);
		} catch (LogicalException e) {
			throw e;
		} catch  (ClassCastException e) {
			throw new PhisicalException("Bad args.", e );
		}
		
		return airbus;
	}

}
