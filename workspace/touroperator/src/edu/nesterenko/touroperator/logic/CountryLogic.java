package edu.nesterenko.touroperator.logic;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.validation.ValidationException;
import edu.nesterenko.touroperator.validation.Validator;

import java.util.List;

import edu.nesterenko.touroperator.dao.CountryDao;
import edu.nesterenko.touroperator.dao.DaoException; 

public class CountryLogic {
	
	private CountryLogic() {}
	
	public static void addCountry(String name) throws LogicException {

		try {
			Validator.checkOnlyLatters(name);
			Country country = new Country(0, name);
			CountryDao countryDao = new CountryDao();
			countryDao.add(0, country);				
		} catch (DaoException | ValidationException e) {
			throw new LogicException(e);
		}
	}
	
	public static List<Country> findAll() throws LogicException {
		CountryDao countryDao = new CountryDao();
		try {
			return countryDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}
}
