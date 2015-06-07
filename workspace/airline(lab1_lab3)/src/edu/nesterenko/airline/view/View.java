package edu.nesterenko.airline.view;


import edu.nesterenko.airline.bean.Request;
import edu.nesterenko.airline.bean.RequestEnum;
import edu.nesterenko.airline.bean.Response;
import edu.nesterenko.airline.command.CommandEnum;
import edu.nesterenko.airline.controller.Controller;
import edu.nesterenko.airline.entity.Manufacturer;

public class View {
	private Controller controller;
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public View(Controller controller, String filePath) {
		setController(controller);
		setFilePath(filePath);
	}
	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void addAirbus(Manufacturer manufacturer, String model, int maxRange, int capacity, int bearingCapacity, int fuelConsumption,
		      int seatsCount, int classCount, int luggageCapacity) {
		Object[] args = {manufacturer, model, maxRange, capacity, bearingCapacity, fuelConsumption, seatsCount, classCount, luggageCapacity};
		CommandEnum command = CommandEnum.ADD_AIRBUS;
		Request request = new Request();
		request.setParameter(RequestEnum.ARGS, args);
		Response response = controller.sendReques(command, request);
		Reporter.report(command, response, filePath);
	}
	
	public void addFreighter(Manufacturer manufacturer, String model, int maxRange, int capacity, int bearingCapacity, int fuelConsumption,
			 int cargoHoldCount) {
		Object[] args = {manufacturer, model, maxRange, capacity, bearingCapacity, fuelConsumption, cargoHoldCount};
		CommandEnum command = CommandEnum.ADD_FREIGHTER;
		Request request = new Request();
		request.setParameter(RequestEnum.ARGS, args);
		Response response = controller.sendReques(command , request);
		Reporter.report(command, response, filePath);
	}
	
	public void deleteAirplane(int index) {
		CommandEnum command = CommandEnum.DELETE;
		Request request = new Request();
		request.setParameter(RequestEnum.INDEX, index);
		Response response = controller.sendReques(command , request);
		Reporter.report(command, response, filePath);
	}
	
	public void findAll() {
		CommandEnum command = CommandEnum.FIND_ALL;
		Response response = controller.sendReques(command , null);
		Reporter.report(command, response, filePath);
	}
	
	public void findByFuelConsumption(int minRange, int maxRange) {
		CommandEnum command = CommandEnum.FIND_BY_FUEL_CONSUMPTION;
		Request request = new Request();
		request.setParameter(RequestEnum.MIN_RANGE, minRange);
		request.setParameter(RequestEnum.MAX_RANGE, maxRange);
		Response response = controller.sendReques(command , request);
		Reporter.report(command, response, filePath);		
	}
	
	public void calculateGeneralBearingCapacity() {
		CommandEnum command = CommandEnum.CALCULATE_GENERAL_BEARING_CAPACITY;
		Response response = controller.sendReques(command , null);
		Reporter.report(command, response, filePath);	
	}
	
	public void calculateGeneralCapacity() {
		CommandEnum command = CommandEnum.CALCULATE_GENERAL_CAPACITY;
		Response response = controller.sendReques(command , null);
		Reporter.report(command, response, filePath);	
	}
	
	public void sortAirplanesByMaxRange() {
		CommandEnum command = CommandEnum.SORT_AIRPLAINS_BY_MAX_RANGE;
		Response response = controller.sendReques(command , null);
		Reporter.report(command, response, filePath);	
	}
	
	public void loadFromHardcode() {
		CommandEnum command = CommandEnum.LOAD_FROM_HARDCODE;
		Response response = controller.sendReques(command , null);
		Reporter.report(command, response, filePath);	
	}
	
	public void loadWithSaxParser(String filePath) {
		CommandEnum command = CommandEnum.LOAD_WITH_SAX;
		Request request = new Request();
		request.setParameter(RequestEnum.FILE_PATH, filePath);
		Response response = controller.sendReques(command , request);
		Reporter.report(command, response, this.filePath);	
	}
	
	public void loadWithStaxParser(String filePath) {
		CommandEnum command = CommandEnum.LOAD_WITH_STAX;
		Request request = new Request();
		request.setParameter(RequestEnum.FILE_PATH, filePath);
		Response response = controller.sendReques(command , request);
		Reporter.report(command, response, this.filePath);	
	}
	
	public void loadWithDomParser(String filePath) {
		CommandEnum command = CommandEnum.LOAD_WITH_DOM;
		Request request = new Request();
		request.setParameter(RequestEnum.FILE_PATH, filePath);
		Response response = controller.sendReques(command , request);
		Reporter.report(command, response, this.filePath);	
	}
}
