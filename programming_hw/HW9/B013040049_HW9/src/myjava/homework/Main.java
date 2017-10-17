package myjava.homework;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Book b1 = new Book("Things Fall Apart", "Chinua Achebe", "1958/1/1");
		Book b2 = new Book("Fairy tales", "Hans Christian Andersen", "1835/1/1");
		
		Bookshelf rawBs = new Bookshelf();
		rawBs.addBooks(b1);
		rawBs.addBooks(b2);
		System.out.println(rawBs);
		
		LibraryManage LibraryManageSystem = new LibraryManage();
		LibraryManageSystem.addBookshelf(rawBs);
		
		Student Allen = new Student("Allen");
		Allen.goToBookshelf(rawBs);
		Allen.decideLookupWays (new LookupByTitle());
		Allen.lookup (b1);
		Allen.lookup (b2);
		System.out.println (Allen + "\n");
		
		LibrarianPartTime Jenny = new LibrarianPartTime ("Jenny");
		Jenny.setLibraryManageSystem(LibraryManageSystem);
		Jenny.borrowBook(Allen);
		System.out.println ();
		
		Student Bob = new Student("Bob");
		Bob.goToBookshelf(rawBs);
		Bob.decideLookupWays (new LookupByTitle());
		Bob.lookup (b2);
		System.out.println (Bob.toString() + "\n");
		
		System.out.println ("Allen wants to return the book now");
		Allen.addReturnBooks(b2);
		Jenny.returnBook (Allen);
	}

}
