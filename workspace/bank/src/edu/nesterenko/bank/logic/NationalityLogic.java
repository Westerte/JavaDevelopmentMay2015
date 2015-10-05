package edu.nesterenko.bank.logic;

import java.util.List;

import edu.nesterenko.bank.dao.DaoException;
import edu.nesterenko.bank.dao.NationalityDao;
import edu.nesterenko.bank.entity.Nationality;

public class NationalityLogic {
	private NationalityLogic() {}
	
	public static List<Nationality> findAll() throws LogicException {
		NationalityDao nationalityDao = new NationalityDao();
		List<Nationality> nationalityList;
		try {
			nationalityList = nationalityDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		
		return nationalityList;
	}
}
