package flowControl;

import java.util.ArrayList;

public class ForLoop {
	 byte o = 1;
	public static void main(String[] args){
		int d=5;
		int f = 12; 
		char p='e';
		String s = ".";
		
		for(int c=10, k=1; d>=k || c<5; d+=1, f++, k+=2, c++, p+=1, s+=s, 
				new ForLoop().printer(), printerS() ){
			System.out.println("d="+d+" k="+k+" p="+((char)p)+" c="+c);
		}
		
		for(int c=10, k=1; false && c<3; d+=1, f++, k+=2, c++, new ForLoop() ){  //dead code
			System.out.println("d="+d+" k="+k);
		}
		
		for(int c=10, k=1; true && c<100; d+=1, f++, k+=2, c++ ){  //Possible
			System.out.println("d="+d+" k="+k);
		}
		
		for (;f<500; f++){
			System.out.println("f="+f);
		}
		
		for (;f<600;){
			System.out.println("f="+f++);			//effectively functioning as a while loop
		}
		
		ArrayList<String> code = new ArrayList<>();
		code.add("hello"); code.add("Abundance"); code.add("wealthy");
		
		for(String val : code){
			System.out.println(val);
			code = new ArrayList<>();
			code.add("laughter");
		}
	}
	
	void printer(){
		if(o<30)
		System.out.println("PRINTING O"+o++);  //different objects generated in for loop so o is added up once for each object
	}
	
	static int z=0;
	
	static void printerS(){
		if(z<30)
		System.out.println("PRINTING z"+z++);
	}

}
