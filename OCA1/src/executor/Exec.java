package executor;

import javaCert.Schedule;
import webDeveloper.MarkSheet;
import static associate.ExamQuestion.marks;  	// you can import static fields for use either individually or everything
import static associate.ExamQuestion.printValues;  //import static associate.ExamQuestion.*; 
public final class Exec {
	MarkSheet paper = new MarkSheet();
	Schedule rice = new Schedule();
	final int set = 10;
	final String name = "Tit bit";
	final StringBuilder joe = new StringBuilder("jaja");
	public Exec() {
		marks = 1000;
		printValues(marks);				// even though this value is in an entirely different package, you can initialize static 
										//variables or methods within another class - because they are universal (bankVault)
		//paper.countPages();				// the countPages method cannot be accessed because it is declared private from MarkSheet
		//new Person();					// Person is an abstract method, it cannot be instantiated!
		
		//set = 15;						//set has been declared final, it can't be reassigned
		
		name.replace("bit", "rit");   	//calling method on name will compile even though it has been declared final

		joe.reverse();					// this method will also compile but...
		
		//joe = new StringBuilder("math");	// this will not compile because, you cannot declare a new object to the final joe
		System.out.println(joe);
		
		int a = roundaBout();
		System.out.println("rounaBout = "+a);
		a = a*20;
		
	}
	
		public final int roundaBout(){
			int r = 3902;
		return r;
		}

	//public abstract void bingo();		//You cannot declare an abstract method in non-abstract/standard class
	
	static public void main (String[] args){
		new Exec();
		Person person;
		}
	}

	class yam extends Person{		// this explains why all this actionlisteners are abstract classes
		@Override
		public void method() {
			System.out.println("Abstract method person is not being implemented here");
			
		}

		@Override
		public void doubleClick() {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	//class bread extends Exec{}		// Exec is not extendable because it has been declared as final, infact it's general output
										//cannot be altered just as all final cases
	