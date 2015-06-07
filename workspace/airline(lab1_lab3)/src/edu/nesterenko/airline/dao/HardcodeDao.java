package edu.nesterenko.airline.dao;

import edu.nesterenko.airline.creator.AirlinerCreator;
import edu.nesterenko.airline.creator.FreighterCreator;
import edu.nesterenko.airline.entity.Manufacturer;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirlineEditor;

public class HardcodeDao implements DataAccessable {

	@Override
	public void loadDataFromSource(Object ... args) throws PhisicalException, LogicalException {
		AirlineEditor.addAirplane(new FreighterCreator(), new Object[] {"EW-256PJ", Manufacturer.AIRBUS, "a380" , 7, 6, 5, 4, 3});
		AirlineEditor.addAirplane(new AirlinerCreator(), new Object[] {"EW-256PO", Manufacturer.BOEING, "747", 1, 2, 3, 4, 5, 6, 7});
		AirlineEditor.addAirplane(new AirlinerCreator(), new Object[] {"EW-254PJ", Manufacturer.BOEING, "777", 7, 6, 5, 4, 3, 2, 1});		
		AirlineEditor.addAirplane(new FreighterCreator(), new Object[] {"EW-777PA", Manufacturer.AIRBUS, "a320", 1, 2, 3, 4, 5});
	}

	@Override
	public void saveDataToSource(Object ... args) {
		throw new UnsupportedOperationException();
	}

}