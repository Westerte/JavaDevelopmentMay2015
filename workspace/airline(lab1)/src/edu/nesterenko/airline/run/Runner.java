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
		view.loadWithSaxParser("xml\\AirlineXML.xml");
		view.calculateGeneralBearingCapacity();
		view.calculateGeneralCapacity();
		view.findAll();
		view.deleteAirplane(2);
		view.calculateGeneralBearingCapacity();
		view.calculateGeneralCapacity();
		view.findByFuelConsumption(1, 7);
		view.sortAirplanesByMaxRange();
		view.findAll();
	}
}
