package myjava.homework;

public class hw1 {

	public static void main(String[] args) {
		double answer;
		double t;
		double g=9.8;
		int height = 50;
		t = ((2*height)/g);
		
		answer = Math.sqrt(t);
		
		System.out.println("Height: " + height +" m");
		System.out.println("Gravitational acceleration = " + g + " m/s^2");
		System.out.println("The book will fall to the ground at [" + answer + "] seconds.");
		// TODO Auto-generated method stub

	}

}
