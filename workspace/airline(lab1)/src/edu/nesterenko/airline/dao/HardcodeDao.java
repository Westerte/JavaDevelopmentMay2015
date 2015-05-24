package edu.nesterenko.airline.dao;

import edu.nesterenko.airline.entity.Model;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirbusCreator;
import edu.nesterenko.airline.logic.AirlineEditor;
import edu.nesterenko.airline.logic.FreighterCreator;

public class HardcodeDao implements DataAccessable {

	@Override
	public void loadDataFromSource(Object ... args) throws PhisicalException, LogicalException {
		AirlineEditor.addAirplane(new FreighterCreator(), new Object[]{Model.AIRBUS_A320, 7, 6, 5, 4});
		AirlineEditor.addAirplane(new AirbusCreator(), new Object[]{Model.BOEING_747, 1, 2, 3, 4, 5, 6, 7});
		AirlineEditor.addAirplane(new AirbusCreator(), new Object[]{Model.BOEING_777, 7, 6, 5, 4, 3, 2, 1});		
		AirlineEditor.addAirplane(new FreighterCreator(), new Object[]{Model.AIRBUS_A380, 1, 2, 3, 4});
	}

	@Override
	public void saveDataToSource(Object ... args) {
		throw new UnsupportedOperationException();
	}

}
