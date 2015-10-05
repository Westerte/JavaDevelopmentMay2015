package edu.nesterenko.touroperator.logic;

import edu.nesterenko.touroperator.dao.ClientDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.validation.ValidationException;
import edu.nesterenko.touroperator.validation.Validator;

public class SignUpLogic {
	private SignUpLogic() {	}

	public static Client signUp(String login, String password, 
			String repeatedPassword, String email) throws LogicException {
		Client client;
		try {
			Validator.checkLogin(login);
			Validator.checkPassword(password);
			Validator.checkPassword(repeatedPassword);
			Validator.checkEmail(email);
			ClientDao clientDao = new ClientDao();
				client = clientDao.registerClient(login, password, repeatedPassword, email, "USER");
		} catch (DaoException | ValidationException e) {
			throw new LogicException(e);
		}
		return client;		
	}
}

