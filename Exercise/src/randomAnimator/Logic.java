package randomAnimator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Logic extends AbstractLogic{

	public Logic() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void randomize() {
		//System.out.println("Values can be randomized");
	}

	@Override
	String stringer(int r, String... love) {
		String yes = r+" "+love[1];
		String no = love[0];
		return (yes+" ... "+no);
	}
	
	char overloaded(int x){
		//System.out.println("I print out and return int: "+x);
		return (char)x;
	}
	
	char overloaded(double y){
		//System.out.println("I print out and return double: "+y);
		return (char)y;
	}

	@Override
	char overloaded(byte... z) {
		//System.out.println("I print out and return byte array");
		return 0;
	}

	@Override
	void overloaded(ArrayList c) {
		//System.out.println("I dont return, I print only arraylist: "+c);
	}

	@Override
	Calendar overloaded(Date date) {
		//System.out.println("I print out and return Calendar");
		return Calendar.getInstance();
	}

	@Override
	double matters(int... g) {
		//System.out.println("I print out and return integer arrays");
		double f = g[0]= 12;
		return f;
	}

	@Override
	char overloaded(String w) {
		//System.out.println("I print out and return Strings: "+w);
		return 101;
	}

	@Override
	char overloaded(double y, int d) {
		//System.out.println("I print out and return double and int: "+(y*d));
		return (char)(y*d);
	}

	@Override
	char overloaded(int y, double d) {
		//System.out.println("I print out and return int and double: "+(y*d));
		return (char)(y*d);
	}

	@Override
	protected char overloaded(boolean c) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
