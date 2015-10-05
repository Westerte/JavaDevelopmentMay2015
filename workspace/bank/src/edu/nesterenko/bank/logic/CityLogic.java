package edu.nesterenko.bank.logic;

import java.util.List;

import edu.nesterenko.bank.dao.CityDao;
import edu.nesterenko.bank.dao.DaoException;
import edu.nesterenko.bank.entity.City;

public class CityLogic {
	
	private CityLogic() {}
	
	public static List<City> findAll() throws LogicException {
		CityDao cityDao = new CityDao();
		List<City> cityList;
		try {
			cityList = cityDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		
		return cityList;
	}
}
