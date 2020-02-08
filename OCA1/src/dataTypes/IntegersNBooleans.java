/*267 in base 8 or octal is rep first by '0'
 * 267/8 - 3
 *  33/8 - 1
 *   4/8 - 4
 *   
 *   413 base 8, however to represent in coding, there is no subscript, so we say 0413
 *   Likewise for hexadecimal, we say 0x10b or 0X10B
 *   Also note that any number from -128 to 127 is actually assigned to byte... however, we can assign it to int
 */
package dataTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class IntegersNBooleans {
	int Decimal = 267;		// primitive variables store actual values but... reference variables store addresses/location
	int octVal = 0413;
	int hexVal = 0x10B;
	int binVal = 0b100001011;  
	// the underscore only makes it more readable, nothing serious -you can't place underscore right after the prefix of bin & hex
	long baseDecimal = 100_267_760L;
	long octwVal = 04_13;
	long hexwVal = 0x10_BA_75;
	long binwVal = 0b1_0000_10_11;

	double orbit = 127.12_3D;    //not 127_.12_D
	float line = 88_22.093_8172F;
	int a, b, c=0;
	File characters = new File("characters");
	File unicodeAssigned = new File("Assigned Characters in Unicode");
	PrintStream print;
	Scanner bik;
	public IntegersNBooleans() {
		try {
			print  = new PrintStream(characters);
			bik = new Scanner(characters);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if(!characters.exists()){
			try {
				characters.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		while(a<165535){
			print.println(a+"="+(char)a);// + "  "+(a+1)+ "= "+(char)(a+1)+ "  "+(a+2)+ "= "+(char)(a+2)+
				//	"  "+(a+3)+ "= "+(char)(a+3)+ "  "+(a+4)+ "= "+(char)(a+4));
			
			a=a+1;
		}
		print.flush();
		print.close();
		char _l_ = '\u0122';
		
		try {
			print = new PrintStream(unicodeAssigned);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(bik.hasNextLine()){
			String z = bik.nextLine();
			
			if(!z.contains("?")){
				if(c<5)	print.print(z+"	");			// if and else can be used directly if it is a single action, you will have to nest it if you want to perform multiple actions
				else {
					print.print("\n");
					c=-1;
				}
				c++;
			}
		}
		print.flush();
		print.close();
	}

	/**
	 * while(b<100000){
			char f = (char)b;
			if(f!='\u0000'){
				if(c<5)	print.print(b+"= "+f+"  ");			// if and else can be used directly if it is a single action, you will have to nest it if you want to perform multiple actions
				else {
					print.print("\n -");
					c=-1;
				}
				c++;
			}
			b++;
		}
		
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new IntegersNBooleans();		//reference variables store addresses/location: Dog without a leash
	}

}
