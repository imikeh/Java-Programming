package myjava.homework.Q2;

public class Rental {
	
	private int dayRented;
	Movie choosemovie;
	
	public Rental(Movie m,int dayRented){
		this.choosemovie = m;
		this.dayRented = dayRented;
	}
	
	public double getcharge(){
		return choosemovie.getCharge(dayRented);
	}
	
	public int getFrequentRenterPoints(){
		return choosemovie.getFrequentRenterPoints(dayRented);
	}

}
