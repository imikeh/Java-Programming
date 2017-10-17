package myjava.homework.part2;

import java.util.Scanner;

public class SecretFiles {

	public static void main(String[] args) {
		
		String name=null,e=null;
		boolean gender=false;
		int age=0;
		String id=null;
			
		Scanner scanner = new Scanner(System.in);
	    
		System.out.println("Create a unique bad guy's detail now.");
		System.out.printf("Name:");
        name = scanner.next();
        System.out.print("Gender(male/female):");
        e = scanner.next();
        if(e=="male"){
        	gender = false;
        }
        else{
        	gender = true;
        }
		System.out.print("Age:");
		age = scanner.nextInt();
		System.out.print("ID:");
		id = scanner.next();
		
		System.out.printf("\n");
		
		scanner.close();
		
		UniqueCriminal.getInstance(name,gender,age,id);
		
		System.out.println("Check the Detail:");
		
		UniqueCriminal.printData();
	
	}

}
