package edu.nesterenko.touroperator.logic;

import java.util.List;

import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.dao.RestTypeDao;
import edu.nesterenko.touroperator.entity.RestType;
import edu.nesterenko.touroperator.validation.ValidationException;
import edu.nesterenko.touroperator.validation.Validator;

public class RestTypeLogic {
	
	private RestTypeLogic() {}
	
    public static void addRestType(String name, String description) 
			 throws LogicException {
		try {
			Validator.checkOnlyLatters(name);
			RestType restType = new RestType(0, name, description);
			RestTypeDao restTypeDao = new RestTypeDao();
			restTypeDao.add(0, restType);				
		} catch (DaoException | ValidationException e) {
			throw new LogicException(e);
		}
	}
	 
	public static List<RestType> findAll() throws LogicException {
		RestTypeDao restTypeDao = new RestTypeDao();
		try {
			return restTypeDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}
}
