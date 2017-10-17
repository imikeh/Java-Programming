package myjava.homework;

import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class hw2 {

	public static void main(String[] args)throws IOException {
		
		String name,id;
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.printf("Please input customer's name:\n");
		name = scanner.nextLine();
		
		System.out.printf("Please input member id:\n");
		id = scanner.nextLine();
		
		System.out.println("[Shopping bill]");
		System.out.printf("Customer: " + name + "(" + id + ")\n");
		scanner.close();
		
		System.out.printf("%-30s%-15s%-15s%-15s\n","Product","Quantity","Price","Total");
		
		FileReader point = new FileReader("src/myjava/homework/bill.dat");
		BufferedReader file  = new BufferedReader(point);
		
		String text ="";
		double answer=0;
		double tax = 0.125;
		
		while((text = file.readLine())!=null){
			String[] put = text.split(";");
			System.out.printf("%-30s%-15s%-15.2f",put[0],put[1],Double.valueOf(put[2]));
			
			int Quantity = Integer.valueOf(put[1]);
			double Price = Double.valueOf(put[2]);
			
			System.out.printf("%-15.2f\n",Quantity*Price);
			answer = answer + Quantity*Price;
		}
		file.close();
		System.out.println("");
		System.out.printf("%-60s%-15.2f","Subtotal",answer);
		System.out.println("");
		
		System.out.printf("%-60s%-15.2f\n","12.5% sales tax",answer*tax);
		System.out.printf("%-60s%-15.2f\n","Total",answer+answer*tax);
		
		// TODO Auto-generated method stub
		scanner.close();
	}

}
