package edu.nesterenko.airline.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import edu.nesterenko.airline.creator.AirlinerCreator;
import edu.nesterenko.airline.creator.FreighterCreator;
import edu.nesterenko.airline.entity.Manufacturer;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirlineEditor;

public class StaxParserDao implements DataAccessable {
	private XMLStreamReader reader;
	private Manufacturer manufacturer;
	private String numberPlate;
	private String model;
	private int maxRange;
	private int capacity;
	private int bearingCapacity;
	private int fuelConsumption;
	private int seatsCount;
	private int classCount;
	private int luggageCapacity;
	private int cargoHoldCount;
	
	@Override
	public void loadDataFromSource(Object... args) throws PhisicalException,
			LogicalException {
		String filePath = (String)args[0];
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			reader = inputFactory.createXMLStreamReader(new FileInputStream(filePath));
			parsing();
		} catch (FileNotFoundException | XMLStreamException e) {
			throw new PhisicalException(e);
		} 
	}
	
	private void parsing() throws PhisicalException {
		try {
			while (reader.hasNext()) {
			    int type = reader.next();
			    switch (type) {
			    case XMLStreamConstants.START_ELEMENT:
			    	fillField();		    	
			    break;
			    case XMLStreamConstants.END_ELEMENT:
			    	buildAirplane();
			    break;
			    }
			}
		} catch (XMLStreamException e) {
			throw new PhisicalException(e);
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	private void buildAirplane() {
		String name = reader.getLocalName();
    	switch(TagsEnum.valueOf(name.replace('-', '_').toUpperCase())) {
    	case AIRLINER :
    		addAirliner();
    		break;
    	case FREIGHTER :
    		addFreighter();
    		break;
    	}
	}
	
	@SuppressWarnings("incomplete-switch")
	private void fillField() throws PhisicalException{
		String name = reader.getLocalName();
		try {
			switch(TagsEnum.valueOf(name.replace('-', '_').toUpperCase())) {
	    	case AIRLINER :
	    		numberPlate = reader.getAttributeValue(0);
	    		break;
	    	case FREIGHTER :
	    		numberPlate = reader.getAttributeValue(0);
	    		break;
	    	case MAX_RANGE:
	        	maxRange = Integer.parseInt(reader.getElementText());
	        	break;
	        case CAPACITY:
	        	capacity = Integer.parseInt(reader.getElementText());
	        	break;
	        case BEARING_CAPACITY:
	        	bearingCapacity = Integer.parseInt(reader.getElementText());
	        	break;
	        case FUEL_CONSUMPTION:
	        	fuelConsumption = Integer.parseInt(reader.getElementText());
	        	break;
	        case SEATS_COUNT:
	        	seatsCount = Integer.parseInt(reader.getElementText());
	        	break;
	        case CLASS_COUNT:
	        	classCount = Integer.parseInt(reader.getElementText());
	        	break;
	        case LUGGAGE_CAPACITY:
	        	luggageCapacity = Integer.parseInt(reader.getElementText());
	        	break;
	        case CARGO_HOLD_COUNT:
	        	cargoHoldCount = Integer.parseInt(reader.getElementText());
	        	break;
	        case AIRBUS:
	        	manufacturer = Manufacturer.AIRBUS;
	        	model = reader.getElementText();
	        	break;
	        case BOEING:
	        	manufacturer = Manufacturer.BOEING;
	        	model = reader.getElementText();
	        	break;	
			}
		} catch (XMLStreamException e) {
			throw new PhisicalException(e);
		}
	}
	
	private void addAirliner() {
		try {
			AirlineEditor.addAirplane(new AirlinerCreator(), new Object[] {numberPlate, manufacturer, model, maxRange, capacity, bearingCapacity, 
									  fuelConsumption, seatsCount, classCount, luggageCapacity});
		} catch (PhisicalException | LogicalException e) {}	
	}
	
	private void addFreighter() {
		try {
			AirlineEditor.addAirplane(new FreighterCreator(), new Object[] {numberPlate, manufacturer, model, maxRange, capacity, bearingCapacity, 
				                      fuelConsumption, cargoHoldCount});
		} catch (PhisicalException | LogicalException e) {}
	}

	@Override
	public void saveDataToSource(Object... args) {
		throw new UnsupportedOperationException();
	}

}
