package edu.vadim.test.run;

import java.util.concurrent.atomic.AtomicBoolean;

public class Runner {

	public volatile boolean field1;
	public static AtomicBoolean atomicBoolean = new AtomicBoolean();
	
	public static void main(String[] args) {			
		First first = new First();
		try {
			System.out.println(1);
			throw new Exception();
			System.out.println(2);
			
		} catch
	}
	
	public static final void method1() {
		
	}

}
