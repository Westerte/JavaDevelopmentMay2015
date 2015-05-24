package edu.nesterenko.airline.logic;

import edu.nesterenko.airline.dao.DataAccessable;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public class Loader {
	private Loader() {}
	
	public static void load(DataAccessable dao, Object ... args) throws PhisicalException, LogicalException {
		dao.loadDataFromSource(args);
	}
}
