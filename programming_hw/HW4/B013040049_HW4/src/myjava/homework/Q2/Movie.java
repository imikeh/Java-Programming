package myjava.homework.Q2;

public class Movie {
	
	private String title;
	public static int NEW_RELEASE = 0;
	public static int REGULAR = 1;
	public static int CHILDRENS = 2;
	public Price price;
	
	public Movie(String title,int whichprice){
		this.title = title;
		setPriceCode(whichprice);
	}
	
	public String movietitle(){
		return this.title;
	}
	
	public double getCharge(int days){
		return price.getCharge(days);
	}
	
	public int getFrequentRenterPoints(int days){
		return price.getFrequentRenterPoints(days);
	}
	
	void setPriceCode(int whichprice){
		switch(whichprice){
		case 0:
			price = new NewReleasePrice();
			break;
		case 1:
			price = new RegularPrice();
			break;
		case 2:
			price = new ChildrenPrice();
			break;
		
		default:
			throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

}
