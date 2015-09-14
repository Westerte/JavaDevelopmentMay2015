package edu.vadim.test.run;

public class Second extends First {
	
	public static synchronized void method2() {
	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("second");
		First.method1();		
	}
}
