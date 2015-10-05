package edu.nesterenko.touroperator.logic;

import edu.nesterenko.touroperator.dao.ClientDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.validation.ValidationException;
import edu.nesterenko.touroperator.validation.Validator;

public class LoginLogic {

	private LoginLogic() {}
	
	public static Client checkClient(String login, String password) throws LogicException {	
		Client client;
		try {
			Validator.checkLogin(login);
			Validator.checkPassword(password);
			ClientDao clientDao = new ClientDao();
			client = clientDao.checkClient(login, password);
			if(client == null) {
				throw new LogicException("Client is null");
			}
			return client;
		} catch (DaoException | ValidationException e) {
			throw new LogicException(e);
		}	
	}
}
