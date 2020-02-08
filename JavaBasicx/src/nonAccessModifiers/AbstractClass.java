package nonAccessModifiers;

abstract public class AbstractClass {
	private int love =10;
	
	/*abstract int vote = 20;  //Illegal modifier for the field vote; only public,
	*protected, private, static, final, transient & volatile are permitted
	*strictfp int happy = 30; //Illegal modifier for the field happy; only public, 
	*protected, private, static, final, transient & volatile are permitted
	*/
	
	public AbstractClass(){
		//this constructor is useless since it cannot be used as an object
	}
	
	abstract void sweetcash();
	
	public void great() { //full method possible in an abstract class
		//method can be used in extended class - check TestNonAccess
		System.out.print("abstract class method is functional");
	}
	//public abstract void moneymachine(){ //Abstract methods do not specify a body}
	
	static void sing(){
		System.out.println("Singing");
	}
	
	void pray(){
		System.out.println("Praying");
	}
}
