package edu.nesterenko.touroperator.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import edu.nesterenko.touroperator.connectionpool.ConnectionPool;
import edu.nesterenko.touroperator.connectionpool.ConnectionPoolException;
import edu.nesterenko.touroperator.entity.Country;
import edu.nesterenko.touroperator.connectionpool.ConnectionWrapper;

public class CountryDao implements AbstractDao<Integer, Country> {
	
	private final static String FIND_ALL = "SELECT * FROM `country`";
	private final static String ADD_NEW = "INSERT INTO `country`"
			+ "(`country_name`) VALUES(?)";
	private final static String SELECT_BY_ID = "SELECT * FROM `country` WHERE `country_id` = ?";
	private final static String DELETE_ELEMENT = "DELETE FROM `country` WHERE `country_id` = ?";
	private final static String UPDATE_ELEMENT = "UPDATE `country` SET `country_name` = ? "
			+ "WHERE `country_id` = ?";
	@Override
	public List<Country> findAll() throws DaoException {
		List<Country> countryList = new ArrayList<Country>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Statement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while(resultSet.next()) {
				countryList.add(makeCountry(resultSet));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return countryList;
	}

	@Override
	public Country findByKey(Integer key) throws DaoException {
		Country country;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, key);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				country = makeCountry(resultSet);
			} else {
				throw new DaoException("No country with such key");
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return country;
	}

	@Override
	public void add(Integer key, Country entity) throws DaoException {
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
	public void delete(Integer key) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(DELETE_ELEMENT);
			statement.setInt(1, key);
			statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		
	}

	@Override
	public void delete(Country entity) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void update(Country entity) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(UPDATE_ELEMENT);
			statement.setString(1, entity.getName());
			statement.setInt(2, entity.getId());
			statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
	}
	
	private Country makeCountry(ResultSet resultSet) throws DaoException {
		try {
			return new Country(resultSet.getInt("country_id"), 
					resultSet.getString("country_name"));
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}