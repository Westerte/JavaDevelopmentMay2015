package edu.nesterenko.touroperator.logic;

import java.util.regex.Pattern;

import edu.nesterenko.touroperator.dao.CityDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.City;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.resource.RegexManager;

public class CityLogic {
	
	private CityLogic() {}
	
	public static void addCity(String name, String description, 
			int countryId) throws LogicException {
		if(name == null || name.isEmpty()) {
			throw new LogicException("name is empty");
		}
		if(Pattern.matches(
				RegexManager.getProperty("pattern.onlylitter"), 
				name)) {
			CityDao cityDao = new CityDao();
			City city = new City(0, name, description, 
					new Country(countryId, null));
			try {
				cityDao.add(0, city);
			} catch (DaoException e) {
				throw new LogicException();
			}
		} else {
			throw new LogicException("Bad params");
		} 	
	}
}
