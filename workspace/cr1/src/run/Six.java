package run;

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
		
		type.self();
		type1.self();
	}	
}