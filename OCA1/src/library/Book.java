package library;

import building.House;

public class Book {
	public String isbn;
	protected String author= "Joseph";
	
	String issueCount = "234IRS";
	
	public Book() {
		System.out.println("Initializing");
	}
	
	protected void modifyTemplate(){
			System.out.println("Functioning and ok ");
		}
	
	public void printBook() {
		System.out.println("First implementation");
		// this can be re-implemented in the derived class
	}
	
	void issueHistory(){}			//default access nested class
	
	public static void main(String[] args){
		new Book();
	}
	
}

class issue{
	// you can have two classes within a Source File but only one must be Public
}
