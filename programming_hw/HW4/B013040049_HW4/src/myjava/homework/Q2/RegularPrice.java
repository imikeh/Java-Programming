package myjava.homework.Q2;

public class RegularPrice extends Price {
	
	public double getCharge(int days){
		double result = 2;
		if(days>2)
			result += (days-2)*1.5;
		return result;
	}

}
