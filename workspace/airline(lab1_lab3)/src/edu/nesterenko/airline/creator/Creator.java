package edu.nesterenko.airline.creator;

import edu.nesterenko.airline.entity.Airplane;
import edu.nesterenko.airline.exception.LogicalException;
import edu.nesterenko.airline.exception.PhisicalException;

public interface Creator {
	public Airplane createAirplane(Object[] object) throws PhisicalException, LogicalException;
}
