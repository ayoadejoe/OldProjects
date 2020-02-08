package library;

public class CourseBook extends Book{
	final boolean bool;		//:Final variables must be initialized before use, otherwise you must initialize it in every constructor
	public CourseBook() {
		bool=false;
		String aut = author;
		modifyTemplate();
		System.out.println("Author is now printing  in CourseBook:"+author);
		//If we decide to rather create a new object: Protected members of class Book are not
		//accessible in derived class StoryBook, if accessed using a new object of class Book
		String x = issueCount;
		issueHistory();

	}
	
	//public CourseBook(int c){	}  You must initialize bool here too
	
	public static void main(String[] args){
		new CourseBook();
	}

}
