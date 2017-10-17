package myjava.homework;

public abstract class Librarian extends Reader {
	//    Librarian will know which system he operates.
	private LibraryManage libraryManageSystem;
	
    public Librarian (String ID) {
    	super (ID);
    }
    
	public void borrowBook (Reader r) {
		/* Use method in LibraryManage to borrow books */
		libraryManageSystem.borrowBooks(r);
	}
	
	public void returnBook (Reader r) {
		/* Use method in LibraryManage to return books */
		libraryManageSystem.returnBooks(r);
	}
	
	public void setLibraryManageSystem (LibraryManage lms) {
		libraryManageSystem = lms;
	}
}
