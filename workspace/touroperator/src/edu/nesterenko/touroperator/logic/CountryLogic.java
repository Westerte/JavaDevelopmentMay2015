package edu.nesterenko.touroperator.logic;

import java.util.regex.Pattern;

import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.resource.RegexManager;
import edu.nesterenko.touroperator.dao.CountryDao;
import edu.nesterenko.touroperator.dao.DaoException; 

public class CountryLogic {
	
	private CountryLogic() {}
	
	public static void addCountry(String name) throws LogicException {
		if(name == null || name.isEmpty()) {
			throw new LogicException("name is empty");
		}
		
		if(Pattern.matches(
				RegexManager.getProperty("pattern.onlylitter"), 
				name)) {
			Country country = new Country(0, name);
			CountryDao countryDao = new CountryDao();
			try {
				countryDao.add(0, country);				
			} catch (DaoException e) {
				throw new LogicException(e);
			}
		} else {
			throw new LogicException("Bad params");
		} 	
	}
}
