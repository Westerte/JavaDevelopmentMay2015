package edu.nesterenko.bank.connectionpool;

import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class ConnectionPool {
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
	private final static String PROPERTIES_FILE = "resources.DBconfig";
	private static AtomicBoolean instanceCreated = new AtomicBoolean();
	private static ConnectionPool instance;		
	private static ReentrantLock lock = new ReentrantLock();
	private BlockingQueue<ConnectionWrapper> connections;
	private final int poolSize;
	
	private ConnectionPool() {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE);		
			poolSize = Integer.parseInt(resourceBundle.getString("db.poolsize"));	
			String user = resourceBundle.getString("db.user");
			String password = resourceBundle.getString("db.password");
			String url = resourceBundle.getString("db.url");
			String driverName = resourceBundle.getString("db.driver");
			Class.forName(driverName);
			connections = new LinkedBlockingQueue<ConnectionWrapper>(poolSize);
			for(int i = 0; i < poolSize; i++) {
				connections.put(new ConnectionWrapper(url, user, password));
			}
		} catch (ClassNotFoundException | InterruptedException | SQLException e) {
			LOG.fatal(e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static ConnectionPool getInstance() { 
		if(!instanceCreated.get()) {
			lock.lock();
			try {
				if(!instanceCreated.get()) {
					instance = new ConnectionPool();
					instanceCreated.set(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}	
	
	public ConnectionWrapper grapConnection() throws ConnectionPoolException {
		try {
			return connections.take();
		} catch(InterruptedException e) {
			throw new ConnectionPoolException(e);
		}
	}
	
	public void releaseConnection(ConnectionWrapper connection) {
		if(connection == null) {
			LOG.error("connection is null");
			return;
		}
		try {
			if(!connections.contains(connection)) {
				connections.put(connection);
			}
		} catch (InterruptedException e) {
			LOG.error(e);
		}
	}

	public void closeConnections()   {
		for(ConnectionWrapper connectionWrapper : connections ) {
			try {
				connectionWrapper.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
}
