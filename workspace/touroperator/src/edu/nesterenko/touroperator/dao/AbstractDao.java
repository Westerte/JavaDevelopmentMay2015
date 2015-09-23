package edu.nesterenko.touroperator.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import edu.nesterenko.touroperator.entity.BusinessEntity;

public interface AbstractDao<K, T extends BusinessEntity> {
	
	public List<T> findAll() throws DaoException;
	public T findByKey(K key) throws DaoException;
	public void add(K key, T entity) throws DaoException;
	public void delete(K key);
	public void delete(T entity);
	public T update(T entity);
	
	default void closeStatement(Statement statement) throws DaoException {
		if(statement == null) {
			throw new DaoException("stetement is null");
		}
		try {
			statement.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
}
