package myjava.homework.Q2;

public class Customer {
	
	private String name;
	int number = 0;
	int i;
	
	public Customer(String name){
		this.name = name;
	}
	
	public Rental[] M = new Rental[100];
	
	public void addRental(Rental r){
		M[number] = r;
		number = number +1;
	}
	
	public String statement(){
		System.out.println("Rental Record for " + this.name);
		for(i=0;i<number;i++){
			System.out.println("	" + M[i].choosemovie.movietitle() + "	" + M[i].getcharge());
		}
		System.out.println("Amount owed is " + getTotalCharge());
		return "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
	}
	
	
	public double getTotalCharge(){
		double total = 0;
		for(i=0;i<number;i++){
			total += M[i].getcharge();
		}
		return total;
	}
	
	public int getTotalFrequentRenterPoints(){
		int total = 0;
		for(i=0;i<number;i++){
			total += M[i].getFrequentRenterPoints();
		}
		return total;
	}

}
