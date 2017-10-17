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
    	Iterator<String> IT = borrowLists.keySet().iterator();
    	String name;
    	while(IT.hasNext()){
    		if((name = IT.next())==r.getID()){ /*查看reader是否真的有在borrowLists裡面*/
    			borrowLists.get(name).addAll(r.getBorrowBooks());
    			return;
    		}
    	}
    	borrowLists.put(r.getID(),new ArrayList<Book>());
    	borrowLists.get(r.getID()).addAll(r.getBorrowBooks());
    }
    
    public void returnBooks (Reader r) {
		/* Remove books in borrowLists by Reader's ID, 
		   and books in Reader's returnBooks
		*/
    	Iterator<String> IT = borrowLists.keySet().iterator();
    	String name;
    	while(IT.hasNext()){
    		if((name = IT.next())==r.getID()){
    			borrowLists.remove(name);
    			return;
    		}
    	}
    }
}
