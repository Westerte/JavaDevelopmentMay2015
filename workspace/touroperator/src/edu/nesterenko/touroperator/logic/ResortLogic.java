package edu.nesterenko.touroperator.logic;

import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.dao.ResortDao;
import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Resort;
import edu.nesterenko.touroperator.validation.Validator;

public class ResortLogic {

	private ResortLogic() {}
	
	public static void addResort(String name, String description, 
			int cityId) throws LogicException {
		if(name == null || name.isEmpty()) {
			throw new LogicException("name is empty");
		}
		if(Validator.checkOnlyLatters(name)) {
			ResortDao resortDao = new ResortDao();
			Resort resort = new Resort(0, name, description, 
					new City(cityId, null, null, null));
			try {
				resortDao.add(0, resort);
			} catch (DaoException e) {
				throw new LogicException();
			}
		} else {
			throw new LogicException("Bad params");
		} 	
	}
}
