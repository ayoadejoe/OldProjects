package flowControl;

public class Switcher {
	public static void main(String[] args){
		
		final byte x=2;
		
		int i= 200000;
		byte b = -128;
		final short s = 1000;
		long l = 1234567891011L;
		
		float f = -2.784f;
		double d = 342.23928;
		Byte bb = 127;		//bigger brother, just like Boolean/Integer but it is for Senior
		Integer ii = 237823257;
		System.out.println(bb);
		boolean bbb = false;
		
		
		appreciate ciate = new appreciate();
		appreciate invite = new appreciate();
		
		switch(s){ //Cannot switch on a value of type appreciate. Only convertible int values, strings or enum variables are permitted
		case x: 
		case i: System.out.println("Integer:"+10);break;
		case b: System.out.println(); break;
		case 1.5:;
		default:break;
		
		}
	}
}

class appreciate{
	
}
