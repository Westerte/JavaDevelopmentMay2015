package edu.nesterenko.touroperator.dao;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

import edu.nesterenko.touroperator.connectionpool.ConnectionPool;
import edu.nesterenko.touroperator.connectionpool.ConnectionPoolException;
import edu.nesterenko.touroperator.connectionpool.ConnectionWrapper;
import edu.nesterenko.touroperator.entity.Client;

public class ClientDao implements AbstractDao<Integer, Client> {

	private static final String SELECT_CLIENT_WITH_LOGIN_PASSWORD = 
			"SELECT * FROM `client` WHERE `client_login` = ? AND `client_password` = ?";
	private static final String INSERT_NEW_CLIENT = 
			"INSERT INTO `client`(client_login, "
			+ "client_password, client_email, client_type) VALUES(?,?,?,?)";
	
	@Override
	public List<Client> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Client> findByKey(Integer key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(Integer key, Client entity) {	
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Integer key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Client entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Client update(Client entity) {
		throw new UnsupportedOperationException();
	}
	
	public Client checkClient(String login, String password) throws DaoException {
		Client client = null;
		ConnectionWrapper connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		try {
			connection = connectionPool.grapConnection();
			preparedStatement = connection.prepareStatement(SELECT_CLIENT_WITH_LOGIN_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				int clientId = resultSet.getInt("client_id");
				String clientType = resultSet.getString("client_type");
				String clientEmail = resultSet.getString("client_email");
				client = new Client(clientId, login, password, clientEmail, clientType);
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(preparedStatement);
			connectionPool.releaseConnection(connection);
		}
		return client;		
	}
	
	public Client registerClient(String login, String password, String repeatedPassword, 
			String email, String clientType) throws DaoException {
		Client client = null;
		ConnectionWrapper connection = null;
		PreparedStatement preparedStatement = null;
		ConnectionPool connectionPool = ConnectionPool.getInstance();		
		try {
			connection = connectionPool.grapConnection();
			preparedStatement = 
					connection.prepareStatement(INSERT_NEW_CLIENT, 
							Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, clientType);
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if(generatedKeys != null && generatedKeys.next()) {
				int clientId = generatedKeys.getInt(1);
				client = new Client(clientId, login, password, email, clientType);
			}			
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(preparedStatement);
			connectionPool.releaseConnection(connection);
		}
		return client;
	}
	
	private String hashPassword(String password) {
		
		return null;
	}
	
	private String makeSalt(String password) {
		SecureRandom random = new SecureRandom();
		int length = random.nextInt(6) + 10;
		char[] salt = new char[length];
		for(int i = 0; i < length; i++) {
			salt[i] = Character.toChars(random.nextInt(Integer.MAX_VALUE)%255);
		}
		return null;
	}
}
