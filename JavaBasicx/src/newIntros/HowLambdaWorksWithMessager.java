package newIntros;

import javax.swing.JOptionPane;

public class HowLambdaWorksWithMessager implements TestInterface{
	
	String message = "I will make it";
	static String several = "We have won ";
	public HowLambdaWorksWithMessager() {
		 
	}
	
	
	public static void main(String[] arg){
		int c= Integer.parseInt(JOptionPane.showInputDialog(null, "How many trophies?"));
	    System.out.println(several+c+((c>1)? " trophies": " trophy"));
	    
	    System.out.println(several.concat(" loves"));
	    several.substring(1,3);
	    several = several+several.substring(1,3);
	    System.out.println(several);
		new HowLambdaWorksWithMessager();
	}


	@Override
	public String messageReturn(String message) {
		// TODO Auto-generated method stub
		return null;
	}

}
