package edu.nesterenko.airline.dao;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.nesterenko.airline.entity.Model;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;
import edu.nesterenko.airline.logic.AirbusCreator;
import edu.nesterenko.airline.logic.AirlineEditor;
import edu.nesterenko.airline.logic.FreighterCreator;

public class SaxParserDao extends DefaultHandler implements DataAccessable {

	private TagsEnum currentTag;
	private Model model;
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
	
	private void addAirbus() throws PhisicalException, LogicalException {
		AirlineEditor.addAirplane(new AirbusCreator(), new Object[] {model, maxRange, capacity, bearingCapacity, 
								                                     fuelConsumption, seatsCount, classCount
								                                     , luggageCapacity});	
	}
	
	private void addFrighter() throws PhisicalException, LogicalException {
		AirlineEditor.addAirplane(new FreighterCreator(), new Object[] {model, maxRange, capacity, bearingCapacity, 
			                                                            fuelConsumption, cargoHoldCount});
	}
	
	@Override 
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) { 
		if(!"airline".equals(qName) && !"airbus".equals(qName) && !"freighter".equals(qName) ) {
			currentTag = TagsEnum.valueOf(qName.toUpperCase()); 
		} else {
			currentTag = null;
		}
    }
    
    @Override
    public void endElement(String uri, String localName,String qName){
    	if("airbus".equals(qName) || "freighter".equals(qName)) {
			currentTag = TagsEnum.valueOf(qName.toUpperCase()); 
		} else {
			currentTag = null;
		}
    }
    
    @Override 
    public void characters(char[] ch, int start, int length) throws SAXException{ 
    	String value = new String(ch, start, length).trim();
    	if (null == currentTag) {
    		return;
    	}
        switch (currentTag) {
        case MODEL:
        	model = Model.valueOf(value.toUpperCase());
        	break;
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
        case AIRBUS:  
			try {
				addAirbus();
			} catch (PhisicalException | LogicalException e) {
				throw new SAXException(e);
			}		
        	break;
        case FREIGHTER:
        	try {
				addFrighter();
			} catch (PhisicalException | LogicalException e) {
				throw new SAXException(e);
			}
        	break;
        }
    }

	@Override
	public void saveDataToSource(Object... args) {
		throw new UnsupportedOperationException();
	}
	
	enum TagsEnum{
	    MODEL, MAX_RANGE, CAPACITY, BEARING_CAPACITY, FUEL_CONSUMPTION,
	    SEATS_COUNT, CLASS_COUNT, LUGGAGE_CAPACITY, CARGO_HOLD_COUNT, AIRBUS, FREIGHTER;
	}

}
