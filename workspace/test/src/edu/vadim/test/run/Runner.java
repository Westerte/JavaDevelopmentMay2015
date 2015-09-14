package edu.vadim.test.run;

import java.util.concurrent.atomic.AtomicBoolean;

public class Runner {

	public volatile boolean field1;
	public static AtomicBoolean atomicBoolean = new AtomicBoolean();
	
	public static void main(String[] args) {			
		First first = new First();
	}
	
	public static final void method1() {
		
	}

}
