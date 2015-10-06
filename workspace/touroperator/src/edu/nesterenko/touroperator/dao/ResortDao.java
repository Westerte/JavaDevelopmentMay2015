package edu.nesterenko.touroperator.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.nesterenko.touroperator.connectionpool.ConnectionPool;
import edu.nesterenko.touroperator.connectionpool.ConnectionPoolException;
import edu.nesterenko.touroperator.connectionpool.ConnectionWrapper;
import edu.nesterenko.touroperator.entity.Resort;

public class ResortDao implements AbstractDao<Integer, Resort> {
	
	private final static String FIND_ALL = "SELECT * FROM `resort`";
	private final static String ADD_NEW = "INSERT INTO `resort`"
			+ "(`resort_name`, `resort_description`, `city_id`)"
			+ " VALUES(?,?,?)";
	private final static String SELECT_BY_ID = 
			"SELECT * FROM `resort` WHERE `resort_id` = ?";
	private final static String DELETE_ELEMENT = "DELETE FROM `resort` WHERE `resort_id` = ?";
	private final static String UPDATE_ELEMENT = "UPDATE `resort` SET `resort_name` = ?, "
			+ "`resort_description` = ?, `city_id` = ? WHERE `resort_id` = ?";

	@Override
	public List<Resort> findAll() throws DaoException {
		List<Resort> countryList = new ArrayList<Resort>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Statement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			CityDao cityDao = new CityDao();
			while(resultSet.next()) {
				countryList.add(new Resort(resultSet.getInt("resort_id"), 
						resultSet.getString("resort_name"), 
						resultSet.getString("resort_description"), 
						cityDao.findByKey(resultSet.getInt("city_id"))));
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
	public Resort findByKey(Integer key) throws DaoException {
		Resort resort;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, key);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int resortId = resultSet.getInt("resort_id");
				String resortName = resultSet.getString("resort_name");
				String description = resultSet.getString("resort_description");
				int cityId = resultSet.getInt("city_id");
				CityDao cityDao = new CityDao();
				resort = new Resort(resortId, resortName, description, 
							cityDao.findByKey(cityId));
			} else {
				throw new DaoException("No resort with such key");
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return resort;
	}

	@Override
	public void add(Integer key, Resort entity) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(ADD_NEW);
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getDescription());
			statement.setInt(3, entity.getCity().getId());
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
	public void delete(Resort entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Resort entity) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(UPDATE_ELEMENT);
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getDescription());
			statement.setInt(3, entity.getCity().getId());
			statement.setInt(4, entity.getId());
			statement.executeUpdate();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
	}

}
