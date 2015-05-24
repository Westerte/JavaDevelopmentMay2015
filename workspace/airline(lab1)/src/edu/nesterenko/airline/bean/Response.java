package edu.nesterenko.airline.bean;

import java.util.HashMap;
import java.util.Map;

public class Response {
	private final Map<ResponseEnum, Object> parameters = new HashMap<>();
	    
    public void setParameter(ResponseEnum key, Object value){
        parameters.put(key, value);
    }
    
    public Object getParameter(ResponseEnum key){
        return parameters.get(key);
    }
}
