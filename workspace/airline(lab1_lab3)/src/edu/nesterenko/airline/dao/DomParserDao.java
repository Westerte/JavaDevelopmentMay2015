package edu.nesterenko.airline.dao;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.nesterenko.airline.creator.AirlinerCreator;
import edu.nesterenko.airline.creator.FreighterCreator;
import edu.nesterenko.airline.entity.Manufacturer;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirlineEditor;

public class DomParserDao implements DataAccessable {

	@Override
	public void loadDataFromSource(Object... args) throws PhisicalException,
			LogicalException {
		String filePath = (String) args[0];		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
	    DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(filePath));
			Element root = document.getDocumentElement();
			NodeList airlinerList = root.getElementsByTagName("airliner");
			for(int i = 0; i < airlinerList.getLength(); i++) {
				Element airlinerElement = (Element)airlinerList.item(i);
				addAirliner(airlinerElement);
			}
			NodeList frighterList = root.getElementsByTagName("freighter");
			for(int i = 0; i < airlinerList.getLength(); i++) {
				Element frighterElement = (Element)frighterList.item(i);
				addFreighter(frighterElement);
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			throw new PhisicalException(e); 
		}
     
	}
	
	private void addAirliner(Element airlinerElement) {
		String manufacturerName = takeManufacturer(airlinerElement);
		Manufacturer manufacturer = Manufacturer.valueOf(manufacturerName.toUpperCase());
		String numberPlate = airlinerElement.getAttribute("number-plate");
		String model = takeElementTextContent(airlinerElement, manufacturerName);
		int maxRange = Integer.parseInt(takeElementTextContent(airlinerElement, "max-range"));
		int capacity = Integer.parseInt(takeElementTextContent(airlinerElement, "capacity"));
		int bearingCapacity = Integer.parseInt(takeElementTextContent(airlinerElement, "bearing-capacity"));
		int fuelConsumption = Integer.parseInt(takeElementTextContent(airlinerElement, "fuel-consumption"));
		int seatsCount = Integer.parseInt(takeElementTextContent(airlinerElement, "seats-count"));
		int classCount = Integer.parseInt(takeElementTextContent(airlinerElement, "class-count"));
		int luggageCapacity = Integer.parseInt(takeElementTextContent(airlinerElement, "luggage-capacity"));
		try {
			AirlineEditor.addAirplane(new AirlinerCreator(), new Object[] {numberPlate, manufacturer, model, maxRange, capacity, bearingCapacity, 
									  fuelConsumption, seatsCount, classCount, luggageCapacity});
		} catch (PhisicalException | LogicalException e) {}	
	}
	
	private void addFreighter(Element frighterElement) {
		String manufacturerName = takeManufacturer(frighterElement);
		Manufacturer manufacturer = Manufacturer.valueOf(manufacturerName.toUpperCase());
		String numberPlate = frighterElement.getAttribute("number-plate");;
		String model = takeElementTextContent(frighterElement, manufacturerName);
		int maxRange = Integer.parseInt(takeElementTextContent(frighterElement, "max-range"));
		int capacity = Integer.parseInt(takeElementTextContent(frighterElement, "capacity"));
		int bearingCapacity = Integer.parseInt(takeElementTextContent(frighterElement, "bearing-capacity"));
		int fuelConsumption = Integer.parseInt(takeElementTextContent(frighterElement, "fuel-consumption"));
		int cargoHoldCount = Integer.parseInt(takeElementTextContent(frighterElement, "cargo-hold-count"));
		try {
			AirlineEditor.addAirplane(new FreighterCreator(), new Object[] {numberPlate, manufacturer, model, maxRange, capacity, bearingCapacity, 
				                      fuelConsumption, cargoHoldCount});
		} catch (PhisicalException | LogicalException e) {}
	}

	private static String takeElementTextContent(Element element, String elementName) {     
		NodeList nList = element.getElementsByTagName(elementName);     
		Node node = nList.item(0);     
		String text = node.getTextContent();     
		return text; 
	}

	private static String takeManufacturer(Element element) {
		String result = null;
		NodeList childNodes = element.getElementsByTagName("boeing");
		if(childNodes.getLength() != 0) {
			result = "boeing";
		}
		childNodes = element.getElementsByTagName("airbus");
		if(childNodes.getLength() != 0) {
			result = "airbus";
		}
		return result;
	}
	@Override
	public void saveDataToSource(Object... args) {
		throw new UnsupportedOperationException();
	}

}
