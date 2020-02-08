package dataTypes;

public class Numeric {
		//Integers
	int c = 1000;
	int f = 10_00;	//compliance is with Java 1.7 or greater
	int k = 0_20;
	short s = 32000;
	//short a = 43000; //-32000 to 32000 approx
	short z = -0B11_000_1111_111_0;
	long baseDecimal = 100_267_760_234L;
	long octal = 0_50_23L;  //
	long hexa = 0x347_A_34_FL;	//Underscores have to be located within digits, not after Ox or before or after L
	
	byte y=-1_0;
	byte i=127;
	//int w = Integer.parseInt("45_98");  //Numberformat exception 
	
	//Floating points
	//int lol = Integer.parseInt("45.98");  //You are parsing a double bro
	double lol = Double.parseDouble("45.9523");
	//float p = 1.232e48F;  Out of range
	float u = 984.7748e-20F;  
	float m = -56.4_98_71e30F; //Underscores have to be located within digits
	
	//double b = 87583.473847899897e347D;  //The literal 87583.473847899897e347D of type double is out of range 
	double b = 87583.473847899897e34D; 
	
	
	public static void main (String[] args){
		System.out.println("Java prints in base 10 = "+new Numeric().b);
		for(byte i= -100; i<11011; i++ ){
			System.out.println("i= "+i);   		//behaves as if in base 10
		}
		
		if(Numeric.class.isInstance(new Numeric())){
		Numeric numeric = new Numeric();
		numeric.m = 2.3434f;
		
		}else{
			
			Numeric numeric = new Numeric();		//local obj ref variable isolated within the if nest
			numeric.m = 3.3434f;
			
		}
		
		
		
	}

}
