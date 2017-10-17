package myjava.homework;

import java.util.ArrayList;

public abstract class Reader {
	protected ArrayList<Book> books;
	//    The book Reader wants to return back
	protected ArrayList<Book> bookReturns;
	//    Reader can look up the book on the bookshelf.
	protected Bookshelf bookshelf;
	//    Derived method for search ways.
	protected Search search; 
	//    Each reader has his unique id, 
	//    not implement yet, use String to Replace by now.
	protected String ID;
	
    
	public void lookup (Book b) {
		bookshelf.search (this, b);
	}
	
	public Reader (String name) {
		books = new ArrayList<Book> ();
		bookReturns = new ArrayList<Book>();
		setID (name);
	}
	
	public void goToBookshelf (Bookshelf bs){
		bookshelf = bs;
	}
	
	public void addBooks (Book b) {
		/* Add book to books */
		books.add(b);
	}
	
	public void removeBooks (Book b) {
		books.remove (b);
	}
	
	public ArrayList<Book> getBorrowBooks () {
		return books;
	}
	
	public void addReturnBooks (Book b) {
		/* Add book to bookReturns */
		bookReturns.add(b);
	}
	
	public ArrayList<Book> getReturnBooks () {
		return bookReturns;
	}
	
	private void setID (String name) {
		ID = name;
	}
	
	public String getID () {
		return ID;
	}
	
	public void decideLookupWays (Search s) {
		search = s;
	}
	
	public Search getSearch () {
		return search;
	}
}
