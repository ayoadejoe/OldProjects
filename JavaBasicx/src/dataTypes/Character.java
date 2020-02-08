package dataTypes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import encapsulation.Constructors;

public class Character {
	
	PrintWriter print;
	File chars;
	String java = "java";
	
	Character() throws IOException{
		chars = new File("char.txt");
		if(!chars.exists())chars.createNewFile();
		
		print = new PrintWriter(chars);
		
		/*for(int x=0; x<=65535; x++){
			print.append("x="+x+" - char "+x+" = "+(char)x+"\n");
			System.out.println("x="+x+" - char "+x+" = "+(char)x);
			Iterating with a short resulted in endless loop:
				x=32766 - char 32766 = ?
				x=32767 - char 32767 = ?
				x=-32768 - char -32768 = ?
				x=-32767 - char -32767 = ?
				_
				x=-2 - char -2 = ?
				x=-1 - char -1 = ?
				x=0 - char 0 = 
				}
			 */
		
	}
	
	static public void main(String args[]) throws IOException{
		Character chaR = new Character();
		char c1 = 122;
		char c2 = '\u0122';					//unicode
		System.out.println("c1 = " + c1);
		System.out.println("c2 = " + c2);
		System.out.println();
		
		Constructors constr = new Constructors();
		Constructors constr2 = new Constructors('c');

		char g = 'f';
		long u = 10;
		int k = g;
		byte d = 7;
		k = d;
		System.out.println("k="+k);
		u = k;
		
	}

}
