package edu.nesterenko.touroperator.logic;

import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.dao.RestTypeDao;
import edu.nesterenko.touroperator.entity.RestType;
import edu.nesterenko.touroperator.validation.Validator;

public class RestTypeLogic {
	private RestTypeLogic() {}
	
	 public static void addRestType(String name, String description) 
			 throws LogicException {
		if(name == null || name.isEmpty()) {
			throw new LogicException("name is empty");
		}
		
		if(Validator.checkOnlyLatters(name)) {
			RestType restType = new RestType(0, name, description);
			RestTypeDao restTypeDao = new RestTypeDao();
			try {
				restTypeDao.add(0, restType);				
			} catch (DaoException e) {
				throw new LogicException(e);
			}
		} else {
			throw new LogicException("Bad params");
		} 	
	}
}
