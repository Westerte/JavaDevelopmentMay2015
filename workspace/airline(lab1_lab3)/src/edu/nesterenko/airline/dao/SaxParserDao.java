package edu.nesterenko.airline.dao;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.nesterenko.airline.creator.AirlinerCreator;
import edu.nesterenko.airline.creator.FreighterCreator;
import edu.nesterenko.airline.entity.Manufacturer;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirlineEditor;

public class SaxParserDao extends DefaultHandler implements DataAccessable {

	private TagsEnum currentTag;
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
	public void loadDataFromSource(Object ... args) throws PhisicalException, LogicalException {
		String filePath = (String) args[0];
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse(new File(filePath), this);
		} catch (ParserConfigurationException | SAXException | IOException e) {
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
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) { 
		if(!"airplanes".equals(qName) && !"airliner".equals(qName) && !"freighter".equals(qName) ) {
			currentTag = TagsEnum.valueOf(qName.replace('-', '_').toUpperCase()); 
		} else {
			if(!"airline".equals(qName)) {
				numberPlate = atts.getValue(0);
			}
			currentTag = null;
		}
    }
    
    @Override
    public void endElement(String uri, String localName,String qName) {
    	if("airliner".equals(qName) || "freighter".equals(qName)) {
			currentTag = TagsEnum.valueOf(qName.toUpperCase()); 
		} else {
			currentTag = null;
		}
    }
    
    @SuppressWarnings("incomplete-switch")
	@Override 
    public void characters(char[] ch, int start, int length) throws SAXException { 
    	String value = new String(ch, start, length).trim();
    	if (null == currentTag) {
    		return;
    	}
        switch (currentTag) {
        case MAX_RANGE:
        	maxRange = Integer.parseInt(value);
        	break;
        case CAPACITY:
        	capacity = Integer.parseInt(value);
        	break;
        case BEARING_CAPACITY:
        	bearingCapacity = Integer.parseInt(value);
        	break;
        case FUEL_CONSUMPTION:
        	fuelConsumption = Integer.parseInt(value);
        	break;
        case SEATS_COUNT:
        	seatsCount = Integer.parseInt(value);
        	break;
        case CLASS_COUNT:
        	classCount = Integer.parseInt(value);
        	break;
        case LUGGAGE_CAPACITY:
        	luggageCapacity = Integer.parseInt(value);
        	break;
        case CARGO_HOLD_COUNT:
        	cargoHoldCount = Integer.parseInt(value);
        	break;
        case AIRLINER:  
			addAirliner();	
        	break;
        case FREIGHTER:
			addFreighter();
        	break;
        case AIRBUS:
        	manufacturer = Manufacturer.valueOf(currentTag.name());
        	model = value;
        	break;
        case BOEING:
        	manufacturer = Manufacturer.valueOf(currentTag.name());
        	model = value;
        	break;
        }
    }

	@Override
	public void saveDataToSource(Object ... args) {
		throw new UnsupportedOperationException();
	}
	
	

}
