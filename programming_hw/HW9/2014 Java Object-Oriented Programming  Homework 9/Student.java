package myjava.homework;

public class Student extends Reader {

	public Student (String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String str = "StudentID is: " + getID() + "\n" +
	    		"Wants to borrow ";
	    if (books.size() == 0) 
	    	str = str + "no books";
	    else
	    if (books.size() == 1) 
	    	str = str + books.size () + " book";
	    else
	    	str = str + books.size () + " books";
	    
	    return str;
	}
}
