package edu.nesterenko.bank.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import edu.nesterenko.bank.entity.Entity;

public interface AbstractDao<K, T extends Entity> {
	final static Logger LOG = Logger.getLogger(AbstractDao.class);
	
	List<T> findAll() throws DaoException;
	T findByKey(K key) throws DaoException;
	void add(K key, T entity) throws DaoException;
	void delete(K key) throws DaoException;
	void delete(T entity);
	void update(T entity) throws DaoException;
	
	default void closeStatement(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
}
