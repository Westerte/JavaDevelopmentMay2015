package edu.nesterenko.touroperator.logic;

import edu.nesterenko.touroperator.dao.ClientDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.validation.Validator;

public class LoginLogic {

	private LoginLogic() {}
	
	public static Client checkClient(String login, String password) throws LogicException {
		Client client = null;
		if(login == null || login.isEmpty()) {
			throw new LogicException("login is emtpy");
		}
		if(password == null || password.isEmpty()) {
			throw new LogicException("password is emtpy");
		}
		if(Validator.checkLogin(login) && Validator.checkPassword(password)) {
			ClientDao clientDao = new ClientDao();
			try {
				client = clientDao.checkClient(login, password);
				if(client == null) {
					throw new LogicException("Client is null");
				}
				return client;
			} catch (DaoException e) {
				throw new LogicException(e);
			}
		} else {
			throw new LogicException("Bad params");
		} 		
	}
}
