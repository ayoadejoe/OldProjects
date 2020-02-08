package encapsulation;

public class Constructors {
	double value = -127;
	int x = 20;
	//initializer block
	{
		int radius = 9;
		double pi = Math.PI;
		double value = pi*(radius^2);
		this.value = value;
		short x = 24;
		this.x = x;
	}
	
	//overloaded constructor
	
	public Constructors(){
		this(2.0f, 0.231);
		
		System.out.println(value);
	}
	
	public Constructors(char c){
		this((int)c);
		System.out.println(c);
	}
	
	private Constructors(int r){
		this(4.5f, 7.49);
	}
	
	protected Constructors(float x, double y){
		System.out.println("Working");
	}

	boolean altima(boolean r){
		altima(false);     //recursive allowed in methods .. endless loop
		return false;
	}
}
