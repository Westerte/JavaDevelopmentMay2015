package edu.nesterenko.touroperator.logic;

import java.util.List;

import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.dao.ResortDao;
import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Resort;
import edu.nesterenko.touroperator.validation.ValidationException;
import edu.nesterenko.touroperator.validation.Validator;

public class ResortLogic {

	private ResortLogic() {}
	
	public static void addResort(String name, String description, 
		int resortId) throws LogicException {
		try {
			Validator.checkOnlyLatters(name);
			ResortDao resortDao = new ResortDao();
			Resort resort = new Resort(0, name, description, 
					new City(resortId, null, null, null));
			resortDao.add(0, resort);
		} catch (DaoException | ValidationException e) {
			throw new LogicException();
		}
	}
	
	public static List<Resort> findAll() throws LogicException {
		ResortDao resortDao = new ResortDao();
		try {
			return resortDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}
}
