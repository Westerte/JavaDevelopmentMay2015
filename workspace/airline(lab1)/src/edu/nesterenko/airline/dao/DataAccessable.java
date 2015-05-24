package edu.nesterenko.airline.dao;

import java.util.List;

import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public interface DataAccessable {
	public void loadDataFromSource(Object ... args) throws PhisicalException, LogicalException;
	public void saveDataToSource(Object ... args);
}
