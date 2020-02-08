package building;

import library.Book;

public class House {
	Book book = new Book();
	
	public House() {						//default access modifier in use for Constructor house
		String isbn = book.isbn;
		book.printBook();
		//String author = book.author;	 not possible because author is protected in package book
		
		//String x = book.issueCount;    	//this is not possible because issueCount has default access in another package
	}

}
