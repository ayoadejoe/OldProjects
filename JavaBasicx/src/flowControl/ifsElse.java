package flowControl;

public class ifsElse {
	static boolean savvy;
	
	public static void main(String [] args){
		if(savvy=true){  			//note there is assignment operator instead comparison op
			System.out.println("savvy is true");
		}
		
		if(savvy);
		else
			System.out.println("bongo");
	}

}
