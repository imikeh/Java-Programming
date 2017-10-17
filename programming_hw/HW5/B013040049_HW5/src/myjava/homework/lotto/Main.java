package myjava.homework.lotto;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int pattern=0;
		int range,winnumber;
		int n=1;
		
		Scanner scanner = new Scanner(System.in);
		
		do{
		System.out.println("Which pattern do you want to play ?");
		System.out.println("1.Draw  2.BigLotto  3.SuperLotto");
		pattern = scanner.nextInt();
		switch(pattern){
		case 1:
			System.out.println("Draw:");
			System.out.println("Please input the range (from 1 ~ ?):");
			range = scanner.nextInt();
			System.out.println("Please input the number of winning numbers:");
			winnumber = scanner.nextInt();		
			if(winnumber > range){
				System.out.println("You are so greedy!!");
				break;
			}
			@SuppressWarnings("unused")
			Draw a = new Draw(winnumber,range) ;
			System.out.println();
			break;
		case 2:
			System.out.println("BigLotto:");
			@SuppressWarnings("unused")
			BigLotto b = new BigLotto(6,49);
			System.out.println();
			break;
		case 3:
			System.out.println("SuperLotto:");
			@SuppressWarnings("unused")
			SuperLotto c = new SuperLotto(6,38);
			System.out.println();
			break;
		}
		}while(n!=0);
		
		scanner.close();

	}

}
