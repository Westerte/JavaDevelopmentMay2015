package edu.nesterenko.touroperator.logic;
import java.util.List;

import edu.nesterenko.touroperator.dao.CityDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.validation.ValidationException;
import edu.nesterenko.touroperator.validation.Validator;

public class CityLogic {
	
	private CityLogic() {}
	
	public static void addCity(String name, String description, 
			String countryId) throws LogicException {
		try {
			Validator.checkOnlyLatters(name);
			int countryIdInteger = Validator.checkInt(countryId);
			CityDao cityDao = new CityDao();
			City city = new City(0, name, description, 
					new Country(countryIdInteger, null));
			cityDao.add(0, city);
		} catch (DaoException | ValidationException e) {
			throw new LogicException();
		} 	
	}
	
	public static List<City> findAll() throws LogicException {
		CityDao cityDao = new CityDao();
		try {
			return cityDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}
}
