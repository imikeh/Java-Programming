package myjava.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BorrowList {
    private HashMap<String, ArrayList<Book>> borrowLists;
    
    public BorrowList () {
    	borrowLists = new HashMap<String, ArrayList<Book>> ();
    }
    
    public void addList (Reader r) {
		/* Insert reader's ID and books as key and value */
		/* to  borrowLists. */
    }
    
    public void returnBooks (Reader r) {
		/* Remove books in borrowLists by Reader's ID, 
		   and books in Reader's returnBooks
		*/
    }
}
