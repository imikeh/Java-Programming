package myjava.homework;

import java.util.ArrayList;
import java.util.Iterator;

public class LibraryManage {
	private ArrayList<Bookshelf> bookshelfs;
	private BorrowList borrowlist;
	
	public LibraryManage (){
		 setBookshelfs ();
		 setBorrowlist ();
	}
	
	public void addBookshelf (Bookshelf bs) {
		bookshelfs.add(bs);
	}
	
	public void borrowBooks (Reader r) {
	    /* Add reader to borrowlist */
		
		/* Change borrowed variable to true in bookshelf */
		
	}
	
	public void returnBooks (Reader r) {
		/* Use method in BorrowList to return books */
		
		/* Change borrowed variable to false in bookshelf */
	}
	
	private void setBookshelfs () {
		bookshelfs = new ArrayList<Bookshelf> ();
	}
	
	private void setBorrowlist () {
		borrowlist = new BorrowList ();
	}
	
}
