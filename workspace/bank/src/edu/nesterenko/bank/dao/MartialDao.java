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
import edu.nesterenko.bank.entity.Martial;

public class MartialDao implements AbstractDao<Integer, Martial> {
	private final static String FIND_ALL = "SELECT * FROM `martial`";
	private final static String ADD_NEW = "INSERT INTO `martial`"
			+ "(`martial_name`) VALUES(?)";
	private final static String SELECT_BY_ID = 
			"SELECT * FROM `martial` WHERE `martial_id` = ?";

	@Override
	public List<Martial> findAll() throws DaoException {
		List<Martial> martialList = new ArrayList<Martial>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Statement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			while(resultSet.next()) {
				martialList.add(new Martial(resultSet.getInt("martial_id"), 
						resultSet.getString("martial_name")));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return martialList;
	}

	@Override
	public Martial findByKey(Integer key) throws DaoException {
		Martial martial;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, key);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int martialId = resultSet.getInt("martial_id");
				String martialName = resultSet.getString("martial_name");
				martial = new Martial(martialId, martialName);
			} else {
				throw new DaoException("No martial with such key");
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return martial;
	}

	@Override
	public void add(Integer key, Martial entity) throws DaoException {
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
	public void delete(Martial entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Martial entity) {
		throw new UnsupportedOperationException();
	}

}