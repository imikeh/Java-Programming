package myjava.homework.Q2;

public class NewReleasePrice extends Price{
	
	public double getCharge(int days){
		return days*3;
	}
	public int getFrequentRenterPoints(int days){
		return (days>1)?2:1;
	}

}
