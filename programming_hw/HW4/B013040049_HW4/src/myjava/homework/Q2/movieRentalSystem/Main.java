package movieRentalSystem;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Movie m1 = new Movie("300: RISE OF AN EMPIRE", Movie.NEW_RELEASE);
		Movie m2 = new Movie("KANO", Movie.REGULAR);
		Movie m3 = new Movie("FROZEN", Movie.CHILDRENS);

		Rental r1 = new Rental(m1, 4);
		Rental r2 = new Rental(m1, 2);
		Rental r3 = new Rental(m3, 7);
		Rental r4 = new Rental(m2, 5);
		Rental r5 = new Rental(m3, 3);

		Customer c1 = new Customer ("David");
		c1.addRental(r1);
		c1.addRental(r4);

		Customer c2 = new Customer ("James");
		c2.addRental(r1);
		c2.addRental(r3);
		c2.addRental(r2);

		Customer c3 = new Customer ("Show");
		c3.addRental(r3);
		c3.addRental(r5);

		Customer c4 = new Customer ("Kevin");
		c4.addRental(r2);
		c4.addRental(r3);
		c4.addRental(r5);

		System.out.println(c1.statement());
		System.out.println(c2.statement());
		System.out.println(c3.statement());
		System.out.println(c4.statement());

	}

}
