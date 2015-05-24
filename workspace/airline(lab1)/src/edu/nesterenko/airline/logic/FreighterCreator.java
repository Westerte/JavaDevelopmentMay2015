package edu.nesterenko.airline.logic;

import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.entity.Freighter;
import edu.nesterenko.airline.entity.Model;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public class FreighterCreator implements Creator {

	@Override
	public Airplane createAirplane(Object[] args) throws PhisicalException,
			LogicalException {
		Airplane freighter = null;
		try {
			Model model = (Model) args[0]; 
			int maxRange = (int) args[1];
			int capacity = (int) args[2];
			int bearingCapacity = (int) args[3];
			int fuelConsumption = (int) args[4];
			int cargoHoldCount = (int)args[4];
			freighter = new Freighter(model, maxRange, capacity, bearingCapacity, fuelConsumption, cargoHoldCount);
		} catch (LogicalException e) {
			throw e;
		} catch (Exception e) {
			throw new PhisicalException("Bad args.", e );
		}
		
		return freighter;
	}

}
