package edu.nesterenko.bank.logic;

import java.util.List;

import edu.nesterenko.bank.dao.DaoException;
import edu.nesterenko.bank.dao.DisabilityDao;
import edu.nesterenko.bank.entity.Disability;

public class DisabilityLogic {
private DisabilityLogic() {}
	
	public static List<Disability> findAll() throws LogicException {
		DisabilityDao disabilityDao = new DisabilityDao();
		List<Disability> disabilityList;
		try {
			disabilityList = disabilityDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		
		return disabilityList;
	}
}
