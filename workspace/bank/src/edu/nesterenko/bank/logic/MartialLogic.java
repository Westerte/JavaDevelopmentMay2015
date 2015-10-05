package edu.nesterenko.bank.logic;

import java.util.List;

import edu.nesterenko.bank.dao.DaoException;
import edu.nesterenko.bank.dao.MartialDao;
import edu.nesterenko.bank.entity.Martial;

public class MartialLogic {
	private MartialLogic() {}
	
	public static List<Martial> findAll() throws LogicException {
		MartialDao martialDao = new MartialDao();
		List<Martial> martialList;
		try {
			martialList = martialDao.findAll();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		
		return martialList;
	}
}
