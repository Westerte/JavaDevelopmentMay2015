package edu.nesterenko.touroperator.logic;

import java.util.regex.Pattern;

import edu.nesterenko.touroperator.dao.ClientDao;
import edu.nesterenko.touroperator.dao.DaoException;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.resource.RegexManager;

public class SignUpLogic {
	
	private SignUpLogic() {	}

	public static Client signUp(String login, String password, 
			String repeatedPassword, String email) throws LogicException {
		
		if(login == null || login.isEmpty()) {
			throw new LogicException("login is emtpy");
		}
		if(password == null || password.isEmpty()) {
			throw new LogicException("password is emtpy");
		}
		if(repeatedPassword == null || repeatedPassword.isEmpty()) {
			throw new LogicException("repeated password is emtpy");
		}
		if(email == null || email.isEmpty()) {
			throw new LogicException("email is emtpy");
		}
		
		if(!password.equals(repeatedPassword) || !Pattern.matches(RegexManager.getProperty("pattern.login"), login) 
		   || !Pattern.matches(RegexManager.getProperty("pattern.password"), password)
		   || !Pattern.matches(RegexManager.getProperty("pattern.email"), email)) {
			throw new LogicException("Bad params");
		}
		ClientDao clientDao = new ClientDao();
		Client client;
		try {
			client = clientDao.registerClient(login, password, repeatedPassword, email, "USER");
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		return client;		
	}
}

