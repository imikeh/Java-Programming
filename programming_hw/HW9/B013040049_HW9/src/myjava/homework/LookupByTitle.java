package myjava.homework;

import java.util.Map;
import java.util.Set;

public class LookupByTitle extends Search {

	@Override
	public boolean lookup(Set<Book> shelfset, Map<String, Boolean> shelf, Book b) {
		// TODO Auto-generated method stub
		/* Please make sure book is in the shelfset (use contains ()),
		/* and is still not borrowed yet (use get ()) 
		*/
		if(shelfset.isEmpty()){ /*�p�G�[�W�O�Ū�*/
			return false;
		}
		if(shelfset.contains(b) &&  shelf.get(b.getTitle())==false){ /*false����٦b�[�W*/
			return true;
		}
		else{
			return false;
		}	
	}

}
