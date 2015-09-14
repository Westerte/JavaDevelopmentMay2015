package edu.vadim.test.run;

public class First {
	public Second second;
	
	public First() {
		System.out.println("Dalboyob");
		new Second();
	}
	
	public static synchronized void method1() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("first");
		Second.method2();		
	}
}
