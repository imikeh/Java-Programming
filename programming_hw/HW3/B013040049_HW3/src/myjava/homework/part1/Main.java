package myjava.homework.part1;

import java.io.FileInputStream;
import java.util.*;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args){
		Scanner file = null;
		try{
			file = new Scanner(new FileInputStream("src/myjava/homework/part1/sample1"));
		}
		catch (FileNotFoundException e){
			System.out.println("File not found.");
			System.exit(0);
		}	
		
		String text = file.nextLine();	
		String[] rc = text.split("x");
	
		int rc0 = Integer.parseInt(rc[0]);
		int rc1 = Integer.parseInt(rc[1]);
		
		int i,j,k;
		int[][] m1  = new int[rc0][rc1];
		
		for(i=0;i<rc0;i++){
			for(j=0;j<rc1;j++){
				m1[i][j] = file.nextInt();
			}
		}
		
		String text2 = file.nextLine();
		
		String text1 = file.nextLine();	
		String[] cr = text1.split("x");
	
		int cr0 = Integer.parseInt(cr[0]);
		int cr1 = Integer.parseInt(cr[1]);
		
		int[][] m2  = new int[cr0][cr1];
		
		for(i=0;i<cr0;i++){
			for(j=0;j<cr1;j++){
				m2[i][j] = file.nextInt();
			}
		}
		
		System.out.println("Matrix1");
		System.out.printf("row: %d\n",rc0);
		System.out.printf("column: %d\n",rc1);
		
		for(i=0;i<rc0;i++){
			for(j=0;j<rc1;j++){
				System.out.printf("%d ",m1[i][j]);
			}
			System.out.printf("\n");
		}
		
		System.out.printf("--------------------\n");
		
		System.out.println("Matrix2");
		System.out.printf("row: %d\n",cr0);
		System.out.printf("column: %d\n",cr1);
		
		for(i=0;i<cr0;i++){
			for(j=0;j<cr1;j++){
				System.out.printf("%d ",m2[i][j]);
			}
			System.out.printf("\n");
		}
		
		System.out.printf("------------------------\n");
		
		if(cr1 == rc0){
	
		int [][] ans = new int[rc0][cr1];
		
		for(i=0;i<rc0;i++){
			for(j=0;j<cr1;j++){
				for(k=0;k<rc1;k++){
					ans[i][j] = ans[i][j] + m1[i][k]*m2[k][j];
				}
			}
		}
		
		for(i=0;i<rc0;i++){
			for(j=0;j<cr1;j++){
				System.out.printf("%d ",ans[i][j]);
			}
			System.out.printf("\n");
		}
		}
		else{
			System.out.println("¤£¥i¬Û­¼");
		}
		

		file.close();

	}

}
