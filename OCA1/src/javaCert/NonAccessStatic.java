package javaCert;

class NonAccessStatic {
	static String name  = "joe";
	String code= "rich";
	static int n = 7;
	public NonAccessStatic() {
		int x = bankVault();				//On the other hand, a non static variable can access/use a static method/variable
	
	}

	static int bankVault(){		//static methods are best used to manipulate or work on static variables 
		int w = 10;
		return w;
	}
	
	int bankVault2(){		//nonstatic method bankVault2 cannot be referenced from a static context
		int w = 10;
		return w;
	}
	
	protected static class address{				//you can't declare a static nested class in non-static one
		
	}
	
	public static void main(String[] arg){	 	
		
		/*this main is a static method defining the entry point of NonAccessStatic
		 * It exists before all other instance of the parent class and is responsible for the instantiation of the 
		 * non-executable class because it's existence like other instance variables is universal and available from
		 * the beginning.
		 */
	
		NonAccessStatic stat = new NonAccessStatic();
		NonAccessStatic stat2 = new NonAccessStatic();
		
		//n = bankVault2();		//A static variable/method cannot access a non-static variable/method because, it does
										//not know if the method exist yet
		
		stat.name = "joe";
		stat2.name = "joseph";
		
		System.out.println("stat.name = "+stat.name+ "    stat2.name = "+stat2.name);
		

		stat.code = "joe";
		stat2.code = "joseph";
		
		System.out.println("stat.code = "+stat.code+ "    stat2.code = "+stat2.code);
		
		/*Result
		 * stat.name = joseph    stat2.name = joseph
		 * stat.code = joe    stat2.code = joseph
		 * 
		 * Note that the static variable retains the last assignment irrespective of the object, however this is not the same
		 * with the non static variable which changes for every new instantiation. Thus, static variables are like for everybody's
		 * public use and alteration where you inherit the status of the last user
		 */
		
	}
}

