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
		borrowlist.addList(r);
		/* Change borrowed variable to true in bookshelf */
		Iterator<Bookshelf> IT = bookshelfs.iterator();
		Bookshelf bs;
		while(IT.hasNext()){
			if((bs=IT.next()).equals(r.bookshelf) && r.getBorrowBooks().size()!=0){
				Iterator<Book> ITT = r.getBorrowBooks().iterator();
				while(ITT.hasNext()){
					bs.changeBookStatus(ITT.next());
				}
					break;
			}
		}
		
	}
	
	public void returnBooks (Reader r) {
		/* Use method in BorrowList to return books */
		borrowlist.returnBooks(r);
		/* Change borrowed variable to false in bookshelf */
		Iterator<Bookshelf> IT = bookshelfs.iterator();
		Bookshelf bs;
		while(IT.hasNext()){
			if((bs=IT.next()).equals(r.bookshelf) && r.getBorrowBooks().size()!=0){
				Iterator<Book> ITT = r.getBorrowBooks().iterator();
				while(ITT.hasNext()){
					bs.changeBookStatus(ITT.next());
				}
					break;
			}
		}
	}
	
	private void setBookshelfs () {
		bookshelfs = new ArrayList<Bookshelf> ();
	}
	
	private void setBorrowlist () {
		borrowlist = new BorrowList ();
	}
	
}
