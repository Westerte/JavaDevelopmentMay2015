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
import edu.nesterenko.touroperator.entity.ResortHotel;

public class ResortHotelDao implements AbstractDao<Integer, ResortHotel> {

	private final static String FIND_ALL = "SELECT * FROM `resort_hotel`";
	private final static String ADD_NEW = "INSERT INTO `resort_hotel`"
			+ "(`resort_hotel_name`, `resort_hotel_description`, `resort_id`,"
			+ " `resort_hotel_stars`) VALUES(?,?,?,?)";
	private final static String SELECT_BY_ID = 
			"SELECT * FROM `resort_hotel` WHERE `resort_hotel_id` = ?";

	@Override
	public List<ResortHotel> findAll() throws DaoException {
		List<ResortHotel> resortHotelList = new ArrayList<ResortHotel>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Statement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FIND_ALL);
			ResortDao resortDao = new ResortDao();
			while(resultSet.next()) {
				resortHotelList.add(new ResortHotel(resultSet.getInt("resort_hotel_id"), 
						resultSet.getString("resort_hotel_name"), 
						resultSet.getString("resort_hotel_description"), 
						resortDao.findByKey(resultSet.getInt("resort_id")), 
								resultSet.getInt("resort_hotel_stars")));
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return resortHotelList;
	}

	@Override
	public ResortHotel findByKey(Integer key) throws DaoException {
		ResortHotel resortHotel;
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, key);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int resortHotelId = resultSet.getInt("resort_hotel_id");
				String resortHotelName = resultSet.getString("resort_hotel_name");
				String description = resultSet.getString("resort_hotel_description");
				int resortId = resultSet.getInt("resort_id");
				int stars = resultSet.getInt("resort_hotel_stars");
				ResortDao resortDao = new ResortDao();
				resortHotel = new ResortHotel(resortId, resortHotelName, description, 
							resortDao.findByKey(resortId), stars);
			} else {
				throw new DaoException("No resort with such key");
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DaoException(e);
		} finally {
			closeStatement(statement);
			connectionPool.releaseConnection(connection);
		}
		return resortHotel;
	}

	@Override
	public void add(Integer key, ResortHotel entity) throws DaoException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		PreparedStatement statement = null;
		ConnectionWrapper connection = null;
		try {
			connection = connectionPool.grapConnection();
			statement = connection.prepareStatement(ADD_NEW);
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getDescription());
			statement.setInt(3, entity.getResort().getId());
			statement.setInt(4, entity.getStars());
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
	public void delete(ResortHotel entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ResortHotel update(ResortHotel entity) {
		throw new UnsupportedOperationException();
	}

}
