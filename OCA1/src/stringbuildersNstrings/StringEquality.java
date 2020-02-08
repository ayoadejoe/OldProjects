package stringbuildersNstrings;

public class StringEquality {
	String ret = null;			//the only reason why you can assign a null to a string is because it is a class,
								//generally you can assign null to objects, because null is a literal value for objects
	public StringEquality() {
		String ram = "joe";
		String tam = "joe";
		
		if(ram == tam) {
			System.out.println(ram == tam);
		}else{
			System.out.println(ram == tam);
		}
		
		String fort = new String("tyb");
		String great = new String("tyb");
		
		System.out.println(fort == great);				//== compares objects
		System.out.println(fort.equals(great));			//.equals compares values in object
		
		//Now, what is the difference between  String = "" and String = new String()
		//in the former case, the object is assigned directly to the value String ret = "laugh";
		//in the latter case, it is defined as a new object even though it has same characters/value so it cannot be equal
		//The pool analysis where java creates objects in pool is the best analysis
		
		/*  NOTE The terms “String constant pool” and “String pool” are used interchangeably
		    and refer to the same pool of String objects. Because String
            objects are immutable, the pool of String objects is also called the “String constant
            pool.” You may see either of these terms on the exam.
		 */
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new StringEquality();
	}

}
