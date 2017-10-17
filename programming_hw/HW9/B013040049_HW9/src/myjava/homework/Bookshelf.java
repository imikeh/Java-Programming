package myjava.homework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Bookshelf {
	private HashSet<Book> shelfSet;
	private HashMap<String, Boolean> shelf;
	
    public Bookshelf () {
    	this.shelfSet = new HashSet<Book> ();
    	this.shelf = new HashMap<String, Boolean> ();
    }
    
	public void addBooks (Book book) {
		/* Add book to shelfSet, and put false to shelf for the book title as value */
		shelfSet.add(book); /*把書籍加入*/
		shelf.put(book.getTitle(), false); /*false表示還在架上，利用gettitle來取得書名字*/
    }

	public HashSet<Book> getShelfSet () {
		return shelfSet;
	}
	
	private HashMap<String, Boolean> getShelf () {
		return shelf;
	}
	
	public void search (Reader r, Book b) {
	/* Using method in Reader's search instance 
	/* to look up if the book is in shelfSet, 
	/* and add into Reader's books. 
	*/
		if(r.getSearch().lookup(r.bookshelf.getShelfSet(),r.bookshelf.getShelf(),b)){ /*要先getsearch才會return search,true就會addbooks,lookup要放入Set,Map,Book*/
			r.addBooks(b);
		}
	}
	
	public boolean getBooKBorrowedStatus (Book b) {
		return shelf.get (b.getTitle());
	}
	
	public void changeBookStatus (Book b) {
		shelf.put (b.getTitle(), !getBooKBorrowedStatus (b));
	}
	
	public String toString () {
		String str = "Bookshelf contains:\n";
		Iterator<Book> bIterator =  shelfSet.iterator();
		while (bIterator.hasNext()) {
			str = str + "==>" + bIterator.next().getTitle() + "\n";
		}
		return str;
	}
}
