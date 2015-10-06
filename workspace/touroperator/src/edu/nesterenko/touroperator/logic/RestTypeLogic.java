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
	
	public static void delete(String id) throws LogicException {
		RestTypeDao restTypeDao = new RestTypeDao();
		try {
			int idInt = Validator.checkInt(id);
			restTypeDao.delete(idInt);
		} catch (DaoException | ValidationException e) {
			throw new LogicException(e);
		}
	}
	
	public static void editRestType(String id, String name, String description) 
			 throws LogicException {
		try {
			int idInteger = Validator.checkInt(id);
			Validator.checkOnlyLatters(name);
			RestType restType = new RestType(idInteger, name, description);
			RestTypeDao restTypeDao = new RestTypeDao();
			restTypeDao.update(restType);				
		} catch (DaoException | ValidationException e) {
			throw new LogicException(e);
		}
	}
	
	public static RestType findByKey(String id) throws LogicException {
		RestType restType;
		try {
			int idInteger = Validator.checkInt(id);
			RestTypeDao restTypeDao = new RestTypeDao();
			restType = restTypeDao.findByKey(idInteger);
		} catch (ValidationException | DaoException e) {
			throw new LogicException(e);
		}
		return restType;
	}
}