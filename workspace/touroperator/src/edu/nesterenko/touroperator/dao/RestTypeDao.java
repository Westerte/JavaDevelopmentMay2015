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
import edu.nesterenko.touroperator.entity.RestType;

public class RestTypeDao implements AbstractDao<Integer, RestType> {
	
	private final static String FIND_ALL = "SELECT * FROM `rest_type`";
	private final static String ADD_NEW = "INSERT INTO `rest_type`"
			+ "(`rest_type_name`, `rest_type_description`) VALUES(?,?)";
	private final static String SELECT_BY_ID = "SELECT * FROM `rest_type` WHERE `rest_type_id` = ?";
	
	@Override
	public List<RestType> findAll() throws DaoException {
		List<RestType> restTypeList = new ArrayList<RestType>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Statement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while(resultSet.next()) {
				restTypeList.add(new RestType(resultSet.getInt("rest_type_id"), 
						resultSet.getString("rest_type_name"), 
						resultSet.getString("rest_type_description")));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return restTypeList;
	}
	
	@Override
	public RestType findByKey(Integer key) throws DaoException {
		RestType restType;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, key);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int restTypeId = resultSet.getInt("rest_type_id");
				String restTypeName = resultSet.getString("rest_type_name");
				String restTypeDescription = resultSet.getString("rest_type_description");
				restType = new RestType(restTypeId, restTypeName, restTypeDescription);
			} else {
				throw new DaoException("No country with such key");
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return restType;
	}
	
	@Override
	public void add(Integer key, RestType entity) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(ADD_NEW);
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getDescription());
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
	public void delete(RestType entity) {
		throw new UnsupportedOperationException();
		
	}
	
	@Override
	public RestType update(RestType entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}