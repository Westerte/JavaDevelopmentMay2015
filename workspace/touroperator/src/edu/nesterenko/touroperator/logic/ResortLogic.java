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
		String cityId) throws LogicException {
		try {
			Validator.checkOnlyLatters(name);
			int cityIdInteger = Validator.checkInt(cityId);
			ResortDao resortDao = new ResortDao();
			Resort resort = new Resort(0, name, description, 
					new City(cityIdInteger, null, null, null));
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
	
	public static void delete(String id) throws LogicException {
		ResortDao resortDao = new ResortDao();
		try {
			int idInt = Validator.checkInt(id);
			resortDao.delete(idInt);
		} catch (DaoException | ValidationException e) {
			throw new LogicException(e);
		}
	}
	
	public static void editResort(String id, String name, String description, 
			String cityId) throws LogicException {
			try {
				int idInteger = Validator.checkInt(id);
				Validator.checkOnlyLatters(name);
				int cityIdInteger = Validator.checkInt(cityId);
				ResortDao resortDao = new ResortDao();
				Resort resort = new Resort(idInteger, name, description, 
						new City(cityIdInteger, null, null, null));
				resortDao.update(resort);
			} catch (DaoException | ValidationException e) {
				throw new LogicException();
			}
	}
	
	public static Resort findByKey(String id) throws LogicException {
		Resort resort;
		try {
			int idInteger = Validator.checkInt(id);
			ResortDao resortDao = new ResortDao();
			resort = resortDao.findByKey(idInteger);
		} catch (ValidationException | DaoException e) {
			throw new LogicException(e);
		}
		return resort;
	}
}
