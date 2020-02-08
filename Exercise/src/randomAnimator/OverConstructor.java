package randomAnimator;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;


public class OverConstructor {
	private int x= 70;
	public String subject = "overloaded constructor";
	public char[] randomChar = {0b1000110, 0_570, 65, 0Xd9};
	String charWord;
	String html;
	//initializer block
	{
		/*System.out.println(">>"+Integer.valueOf(x));
		System.out.println("x="+Integer.toBinaryString(x));
		System.out.println("hex="+Integer.toHexString(217));
		System.out.println("oct="+Integer.toOctalString(376));*/
		charWord = "Word:";
		for (int r=0; randomChar.length>r; r++){
			charWord += randomChar[r]+"";
			System.out.println("randomWord: "+randomChar[r]);
			//System.out.println("charWord: "+charWord);  //=+ is not a valid java operator.  This prints the decimal value instead
		}
	}
	
	void invertigo(byte s){		//Constructor call must be the first statement in a constructor
		//this("yes");			//not allowed, you cannot call the constructor from the method
		int c= 10;			
	}

	public OverConstructor() {
		System.out.println("Finally cally main constructor");
		System.out.println("charWord: "+charWord);
		byte q = 10;
		invertigo(q);
		//The method invertigo(byte) in the type OverConstructor is not applicable for the arguments (int)
		//invertigo(10);		//not allowed, the method parameter is being misconstrued as a int
	}

	private OverConstructor(int value) {
		//this(5);				Recursive constructor invocation OverConstructor(int)
		System.out.println("The value is:"+value);
		System.out.println("charWord: "+charWord);
	}
	
	protected OverConstructor(String value) {
		System.out.println("The value is:"+value);
		System.out.println("charWord: "+charWord+5);
	}
	
	OverConstructor(int value, String value2) {			//default access modifier makes this constructor accessible within package
		System.out.println("The value is:"+value);
		System.out.println("charWord: "+charWord);
	}
	
	public OverConstructor(FaceUp face) {
		this("Hello");		//you call other overload from here giving you the opportunity to initialize them
		System.out.println("No of components:"+face.countComponents());
		face.remove(face.siteDisplay);
		face.siteDisplay.setVisible(false);
		JEditorPane manual = new JEditorPane("text/html", html);
		face.add(manual, BorderLayout.CENTER);
		manual.setVisible(true);
		face.setVisible(true);
		//this("Hello");	If placed here, it won't work	
		//face.dispose();
	}
	
	{
	html = " <!DOCTYPE html><html><body><h2>Spectacular Mountain</h2><img src="+"file:\\c:\\users\\lani\\pictures\\CubeSorb.jpg"+" width=200 height=200></img></body></html>";
	}
	
}
