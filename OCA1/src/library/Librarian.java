package library;

public class Librarian {
	Book book = new Book();
	public Librarian() {
		String c = book.issueCount;
		book.issueHistory();
	}

}
