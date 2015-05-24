package edu.nesterenko.airline.run;

import edu.nesterenko.airline.controller.Controller;
import edu.nesterenko.airline.entity.Model;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirbusCreator;
import edu.nesterenko.airline.logic.Creator;
import edu.nesterenko.airline.view.View;

public class Runner {

	public static void main(String[] args) throws PhisicalException, LogicalException {
		View view = new View(new Controller(), "exitFile.txt");
		view.addAirbus(Model.BOEING_747, 1, 2, 3, 4, 5, 6, 7);
		view.addAirbus(Model.BOEING_777, 7, 6, 5, 4, 3, 2, 1);
		view.addFreighter(Model.AIRBUS_A320 , 1, 2, 3, 4, 6);
		view.addFreighter(Model.AIRBUS_A380 ,6, 4, 3, 2, 1);
		view.calculateGeneralBearingCapacity();
		view.calculateGeneralCapacity();
		view.deleteAirplane(2);
		view.calculateGeneralBearingCapacity();
		view.calculateGeneralCapacity();
		view.findByFuelConsumption(1, 7);
		view.sortAirplanesByMaxRange();
		view.findAll();
	}
}
