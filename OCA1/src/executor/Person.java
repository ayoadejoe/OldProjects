package executor;

public abstract class Person {			//note that abstract final Person is not possible, you can't define both
	//private abstract String deter = "bingo";
	//abstract int y = 0;  		//You cannot declare an abstract variable
	
	public Person(){
		System.out.println("Abstract constructor cannot be instantiated because it is already an abstract class");
		method();
	}

	public abstract void method();		//An abstract method does not define a body... why? because it is implemented by
										//an extended/derived class - check this implementation in Exec
										//if this were to be declared as final, it cannot be implemented in Exec
										//An abstract method may or may not declare an abstract method
	public abstract void doubleClick();
	
	public static void main(String[] args){			//if the main method is not defined as public, there would be an error
		Person person; //=new Person();
		String[] arg = new String[1];				//String[] instantiates a string array object of size 1
		String rag[] = {"rag", "tag"};				//On the other hand, String rag[] behaves as a primitive type assignment
		//arg[0] = "one";
		try{
			System.out.println(rag[1]);
			int i = 0;
			i=Integer.parseInt(rag[0]);
		}catch(NumberFormatException e){
			//System.out.println(i+" can't print");
		}
	}
}
