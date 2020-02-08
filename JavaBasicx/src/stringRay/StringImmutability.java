package stringRay;

import javax.swing.JFrame;
//Strings are immutable. 
public class StringImmutability {//if you name a class String: this happens: Error: Main method not found in class stringRay.String, please define the main method as:
	   //It confused the JVM 
	static int x = 12;
	Object f = StringImmutability.this;
	
	public StringImmutability() {
		righteous(f);
	}
	public boolean righteous(Object object){
		System.out.println(this);
		if(this == object){
			System.out.println("object is equal");
			return true;
		}else System.out.println("unequal");
		
		if(object instanceof String)System.out.println("object is string");
		else return false;
			
		return false;
	}
	
	public static void main(String[] args){ // A main method MUST pass string[]
		String day = "SunDday";
		String way = "Awolowo";
		System.out.println(day.concat(way));
		day.replace('D', 'Z').substring(3);
		System.out.println(day);
		day.concat(" of Joy");
		System.out.println(day);
		day = day.replace('D', 'Z').substring(3);
		System.out.println(day);
		
		String baba = 12 + 10 + "Baba"+15+5+(22/7)+(10+3);
		System.out.println(baba);
		
		int y = StringImmutability.x;
		
		new StringImmutability();

	}

}
