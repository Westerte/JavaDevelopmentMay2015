package edu.nesterenko.airline.dao;

import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public interface DataAccessable {
	public void loadDataFromSource(Object ... args) throws PhisicalException, LogicalException;
	public void saveDataToSource(Object ... args);
}
