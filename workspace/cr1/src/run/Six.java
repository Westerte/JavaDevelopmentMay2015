package run;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Six {
	enum Type {
		ONE, UNI, UNO;
		
		Type() {
			System.out.println("Constructor");
			self();			
		}
		
		private void self() {
			System.out.printf("Self%n");
		}
	}
	
	public static void main(String...args) {
		Six.Type type = Six.Type.ONE;
		Six.Type type1 = Six.Type.UNI;
		for(;;) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("exit.txt")));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}
