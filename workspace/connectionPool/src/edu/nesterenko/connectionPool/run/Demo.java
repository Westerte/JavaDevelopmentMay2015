package edu.nesterenko.connectionPool.run;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import edu.nesterenko.connectionPool.threads.TestThread;

public class Demo {
	
	static {
		new DOMConfigurator().doConfigure("config/log4j.xml", LogManager.getLoggerRepository());
	}
	
	public static void main(String... args) {
		TestThread[] threads = new TestThread[20];
		for(int i = 0; i < 20; i++) {
			threads[i] = new TestThread();
		}
		for(int i = 0; i < 20; i++) {
			threads[i].start();
		}
		for(int i = 0; i < 20; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("bb");
	}
}
