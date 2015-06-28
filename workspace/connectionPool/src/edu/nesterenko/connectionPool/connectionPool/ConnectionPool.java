package edu.nesterenko.connectionPool.connectionPool;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import edu.nesterenko.connectionPool.exception.PhisicalException;

public class ConnectionPool {
	private final static Logger LOG = Logger.getLogger(ConnectionPool.class);
	private final static String PROPERTIES_FILE_PAHT = "config/CPconf.properties"; 
	public final int POOL_SIZE;
	private static ConnectionPool instance  = new ConnectionPool();	
	private BlockingQueue<ConnectionWrapper> connections;
	
	public class ConnectionWrapper {
		private Connection connection;
		private ConnectionWrapper(String url, String user, String password) throws SQLException {	
			 connection = DriverManager.getConnection(url,  user, password);
		}		
		public Connection getConnection() {
			return connection;
		}
	}
	
	private ConnectionPool() {
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(PROPERTIES_FILE_PAHT);
			properties.load(fis);			
			POOL_SIZE = Integer.parseInt(properties.getProperty("cp.poolsize"));			
			String user = properties.getProperty("db.user");
			String password = properties.getProperty("db.password");
			String url = properties.getProperty("db.url");
			String driverName = properties.getProperty("db.driver");
			Class.forName(driverName);
			connections = new LinkedBlockingQueue<ConnectionPool.ConnectionWrapper>(POOL_SIZE);
			for(int i = 0; i < POOL_SIZE; i++) {
				connections.put(new ConnectionWrapper(url, user, password));
			}
		} catch(Exception e) {	
			LOG.fatal(e);
			throw new Error(e);
		}
	}
	
	public static ConnectionPool getInstance() { 
		return instance;
	}	
	
	public ConnectionWrapper grapConnection() throws PhisicalException {
		try {
			return connections.take();
		} catch(InterruptedException e) {
			throw new PhisicalException(e);
		}
	}
	
	public void releaseConnection(ConnectionWrapper connection) {
		try {
			if(!connections.contains(connection)) {
				connections.put(connection);
			}
		} catch (InterruptedException e) {
			// it's impossible by logic
		}
	}
	
	public int getAvailableCount() {
		return connections.size();
	}
}
