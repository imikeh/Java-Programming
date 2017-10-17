package myjava.homework.part2;

import java.util.Scanner;

public class Main {
	
	public enum Producer{
		SYM,KYMCO,YAHAHA;
	}

	public enum Cylinder{
		CC_100,CC_125,CC_150;
	}
	
	public enum Type{
		V2,GT,CYGNUS;
	}
	
	
	public static void main(String[] args) {
		
		int a,b,c;
		Scanner text = new Scanner(System.in);
		
		Producer aa = null;
		Cylinder bb = null;
		Type cc= null;
		
		System.out.print("Which Producer you want(1:SYM 2:KYMCO 3:YAHAHA): ");
		a = text.nextInt();
		System.out.print("Which Cylinder you want(1:100 2:125 3:150): ");
		b = text.nextInt();
		System.out.print("Which Type you want(1:V2 2:GT 3:CYGNUS): ");
		c = text.nextInt();
		
		switch(a){
		case 1:
			aa = Producer.SYM;
			break;
		case 2:
			aa = Producer.KYMCO;
		    break;
		case 3:
			aa = Producer.YAHAHA;
		    break;    
		}
		
		switch(b){
		case 1:
			bb = Cylinder.CC_100;
			break;
		case 2:
			bb = Cylinder.CC_125;
		    break;
		case 3:
			bb = Cylinder.CC_150;
		    break;    
		}
		
		switch(c){
		case 1:
			cc = Type.V2;
			break;
		case 2:
			cc = Type.GT;
		    break;
		case 3:
			cc = Type.CYGNUS;
		    break;    
		}
		
		System.out.println("Your scooter is " + aa + " " + cc + " " + bb );
		// TODO Auto-generated method stub
        text.close();
	}

}
