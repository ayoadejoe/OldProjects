package building;

import library.Book;
import library.CourseBook;

public class StoryBook extends Book{
	//Book book = new Book();
	public StoryBook() {
		String buk = author;
		modifyTemplate();
		printBook();
		System.out.println("Author is now printing in StoryBook:"+author);
		//new CourseBook();		// Initiating this instance here will run the Parent class twice
		
		//book.author;			// there will be trouble here because the author and modify are protected even though StoryBook 
		//book.modifyTemplate();	// parent class is Book, yet, Book's protected contents will be inaccessible because it is in 
								// another package
	}
	
	
	
	//public void printBook(){System.out.println("extended implementation");	}  //can't compile because method was declared
																				//final in parent class
	
	
	@Override
	protected void modifyTemplate() {
		System.out.println("Formerly Functioning and ok, now very OK ");
		super.modifyTemplate();
	}

	// you override a method in the parent class because you want to define it's new property away from the default or the 
	// preprogrammed behavior. By so doing you have extended the code and made it reusable, this is called polymorphism

	@Override
	public void printBook() {
		System.out.println("Renewed implementation ");
		super.printBook();
	}


	static String real = "real";	//A static variable is the common, shared variable that will be used by as many objects
									//instantiated. It exists independently and does not depend on the instantiation of any class.
									// I have to change this to static because, this is a static class
	public static void main(String[] args){
		new StoryBook();
		real = real+" cash";
	}

}
