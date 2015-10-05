package edu.nesterenko.bank.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.nesterenko.bank.connectionpool.ConnectionPool;
import edu.nesterenko.bank.connectionpool.ConnectionPoolException;
import edu.nesterenko.bank.connectionpool.ConnectionWrapper;
import edu.nesterenko.bank.entity.Nationality;

public class NationalityDao implements AbstractDao<Integer, Nationality> {
	private final static String FIND_ALL = "SELECT * FROM `nationality`";
	private final static String ADD_NEW = "INSERT INTO `nationality`"
			+ "(`nationality_name`) VALUES(?)";
	private final static String SELECT_BY_ID = 
			"SELECT * FROM `nationality` WHERE `nationality_id` = ?";

	@Override
	public List<Nationality> findAll() throws DaoException {
		List<Nationality> nationalityList = new ArrayList<Nationality>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Statement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while(resultSet.next()) {
				nationalityList.add(new Nationality(resultSet.getInt("nationality_id"), 
						resultSet.getString("nationality_name")));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return nationalityList;
	}

	@Override
	public Nationality findByKey(Integer key) throws DaoException {
		Nationality nationality;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, key);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int nationalityId = resultSet.getInt("nationality_id");
				String nationalityName = resultSet.getString("nationality_name");
				nationality = new Nationality(nationalityId, nationalityName);
			} else {
				throw new DaoException("No nationality with such key");
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return nationality;
	}

	@Override
	public void add(Integer key, Nationality entity) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(ADD_NEW);
			statement.setString(1, entity.getName());
			statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
	}

	@Override
	public void delete(Integer key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Nationality entity) {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void update(Nationality entity) {
		throw new UnsupportedOperationException();
	}
}
