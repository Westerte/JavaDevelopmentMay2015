package edu.nesterenko.touroperator.dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import edu.nesterenko.touroperator.connectionpool.ConnectionPool;
import edu.nesterenko.touroperator.connectionpool.ConnectionPoolException;
import edu.nesterenko.touroperator.connectionpool.ConnectionWrapper;
import edu.nesterenko.touroperator.entity.Client;
import edu.nesterenko.touroperator.resource.ConfigurationManager;

public class ClientDao implements AbstractDao<Integer, Client> {

	private static final String SELECT_HASH = 
			"SELECT * FROM `client` WHERE `client_login` = ?";
	private static final String INSERT_NEW_CLIENT = 
			"INSERT INTO `client`(client_login, "
			+ "client_hash, client_salt, client_email, client_type) VALUES(?,?,?,?,?)";
	
	@Override
	public List<Client> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Client findByKey(Integer key) {
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
			preparedStatement = connection.prepareStatement(SELECT_HASH);
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				String saltString = resultSet.getString("client_salt");
				String hash = resultSet.getString("client_hash");
				if(hash.equals(hashPasswordWithSalt(password, saltString.getBytes()))) {
					int clientId = resultSet.getInt("client_id");
					String clientType = resultSet.getString("client_type");
					String clientEmail = resultSet.getString("client_email");
					client = new Client(clientId, login, clientEmail, clientType);
				}
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
			byte[] salt = makeSalt();
			String hash = hashPasswordWithSalt(repeatedPassword, salt);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, hash);
			preparedStatement.setString(3, new String(salt));
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, clientType);
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if(generatedKeys != null && generatedKeys.next()) {
				int clientId = generatedKeys.getInt(1);
				client = new Client(clientId, login, email, clientType);
			}			
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(preparedStatement);
			connectionPool.releaseConnection(connection);
		}
		return client;
	}
	
	private String hashPasswordWithSalt(String password, byte[] salt) throws DaoException {
		password += ConfigurationManager.getProperty("crypt.localParameter");
		PBEKeySpec pbe = new PBEKeySpec(password.toCharArray(), salt, 4096, 1024);
		byte[] keyArray;
		try {
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			SecretKey key = secretKeyFactory.generateSecret(pbe);
			keyArray = key.getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new DaoException(e);
		} 
		return new String(keyArray);
	}
	
	private byte[] makeSalt() {
		Random random = new Random();
		int saltLength = 12 + random.nextInt(6); 
		byte[] salt = new byte[saltLength];
		random.nextBytes(salt);
		return salt;
	}
}
