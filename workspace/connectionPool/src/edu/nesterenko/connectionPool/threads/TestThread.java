package edu.nesterenko.connectionPool.threads;

import java.util.Random;

import edu.nesterenko.connectionPool.connectionPool.ConnectionPool;
import edu.nesterenko.connectionPool.exception.PhisicalException;

public class TestThread extends Thread {
	private Random random = new Random();
	
	public void run() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		ConnectionPool.ConnectionWrapper connectionWrapper = null;
		try {
			connectionWrapper = connectionPool.grapConnection();
			System.out.println(String.format("Thread: %s grap connection", this.getName()));
			System.out.println(connectionPool.getAvailableCount());
			Thread.sleep((random.nextInt(2)+1)*1000);
		} catch (PhisicalException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(connectionWrapper != null) {
				connectionPool.releaseConnection(connectionWrapper);
				connectionPool.releaseConnection(connectionWrapper);
				connectionPool.releaseConnection(connectionWrapper);
				connectionPool.releaseConnection(connectionWrapper);
				System.out.println(connectionPool.getAvailableCount());
				System.out.println(String.format("Thread: %s release connection", this.getName()));
			}
		}
	}
}
