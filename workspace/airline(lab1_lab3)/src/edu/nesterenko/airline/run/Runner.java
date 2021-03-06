package edu.nesterenko.airline.run;

import edu.nesterenko.airline.controller.Controller;
import edu.nesterenko.airline.view.View;

public class Runner {

	public static void main(String[] args) {
		View view = new View(new Controller(), "exitFile.txt");
		//view.loadWithDomParser("xml\\AirlineXml.xml");
		//view.loadWithSaxParser("xml\\AirlineXml.xml");
		view.loadWithStaxParser("xml\\AirlineXml.xml");
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
