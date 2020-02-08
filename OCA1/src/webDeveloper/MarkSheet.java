package webDeveloper;

public class MarkSheet {

	public MarkSheet() {
		System.out.println("About to call inner class");
		new innerClass();
		countPages();
	}
	
	private void countPages(){
		System.out.println("No of Pages= 100");
	}
	
}

class innerClass {
	public innerClass(){
		System.out.println("Inner class active");
		//countPages();					// Even though they exist in the same File, this top class cannot access the other class
										// private members
	}

}

