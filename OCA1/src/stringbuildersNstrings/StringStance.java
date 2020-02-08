package stringbuildersNstrings;

import java.util.Random;

public class StringStance {
	Object we = new Random();
	String pk;
	public StringStance() {
		System.out.println(this.getClass());
		if (we instanceof String){
			System.out.println("we is a string");
		}
	}

	
	public static void main(String[] args) {
		new StringStance();
	}

}
