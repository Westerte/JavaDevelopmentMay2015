package run;

public class Main {
	
	private class Seven {
		public Seven() {
			
		}
		private void poly() {
			System.out.println("1");
		}
	}
	
	public static void main(String...args) {
		Seven s = new SubSeven();		
	}
	
	public class SubSeven extends Seven {
		public void poly() {
			System.out.println("2");
		}
	}
}
