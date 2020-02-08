package basic;

import java.util.Date;

public class ToplevelClass {
	java.util.Date eighteen = new java.util.Date();
	java.sql.Date nineteen = new java.sql.Date(1);
	//DefaultClassTest def = new DefaultClassTest();  //This class is in a default package hence, not accessible
	
	String author;
	protected int people;
	public ToplevelClass(){
		System.out.println("in top level constructor");
		bringham james = new bringham();
		james.innocent();
	}

	protected void mopolPeace(){
		
	}
	
	class bringham{	// nested class
		void innocent(){
		System.out.println("nested class operational");
		new gham().testGham();
		}
	}
	
	public static void main(String[] args){
		System.out.println("Initializing main class inside Imports");
		new ToplevelClass();
	}
	
}

class gham{
	void testGham(){
		System.out.println("Testing Gham");
	}

	@Override
	public String toString() {
		//override for StringBuidBuff class
		return "Hello From ToplevelClass";
	}
	
	
	
}
