package edu.nesterenko.airline.bean;

import java.util.HashMap;
import java.util.Map;

public class Request {
	private final Map<RequestEnum, Object> parameters = new HashMap<>();
	
	public void setParameter(RequestEnum key, Object value) {
        parameters.put(key, value);
    }	
    
    public Object getParameter(RequestEnum key) {
        return parameters.get(key);
    }
}
