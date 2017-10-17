package myjava.homework;

import java.util.Scanner;

public class hw3 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		int error,a;
		
		do{
		System.out.println("Please input the length of edge:");
		a = scanner.nextInt();
		if(a%2==0){
			System.out.println("Odd value only.");
			error=1;
		}
		else
			error=0;
		}
		while(error==1);
		
		int choose;
		
		do{
		System.out.println("What image do you want?");
		System.out.printf("    1) Circle\n    2) Donut\n    3) Two traingle\n    4) Exit\n");
		
		choose = scanner.nextInt();
		
		switch(choose){
		
		case 1:
		
		int i,j,m,n,w;
	    
	    m = (a/2)+1;
	    n = a;
	    w = 2*a;

	    for(i=0;i<w;i++){
	        for(j=0;j<w;j++){
	            if(Math.sqrt(((m-i)*(m-i) + (m-j)*(m-j))) <= (n/2) )
	            	System.out.print("*");
	            else
	            	System.out.print(" ");
	        }
	        System.out.println("");
	    }
	    
	    break;
	    
		case 2:
			
		int b = (a-1)/2;
		double s = b/2;
		for(i=1;i<=a;i++){
			for(j=1;j<=a;j++){
				if(b < Math.sqrt((b+1-i)*(b+1-i) + (b+1-j)*(b+1-j)) )
					System.out.printf("*");
				else if(s >= Math.sqrt((b+1-i)*(b+1-i) + (b+1-j)*(b+1-j)) )
					System.out.printf("*");
				else
					System.out.printf(" ");
			}
			System.out.printf("\n");
		}	
		
		break;
	    
		case 3:
		
		int k;
		    m =a-2;
		    n =a/2;
		    
		for(i=1;i<=a/2;i++){
			for(j=0;j<i;j++){
		       	System.out.print("*");
		    }

		    for(k=m;k>0;k--){
		       	System.out.print(" ");
		    }

		    for(j=0;j<i;j++){
		        System.out.print("*");
		    }
		        System.out.print("\n");
		        m=m-2;
		    }

		    for(i=1;i<=a;i++){
		    	System.out.print("*");
		    }

		    System.out.print("\n");

		    m=1;

		    for(i=1;i<=a/2;i++){
		        for(j=n;j>=i;j--){
		        	System.out.print("*");
		        }

		    for(k=0;k<m;k++){
		       	System.out.print(" ");
	        }

	        for(j=n;j>=i;j--){
	        	System.out.print("*");
		    }
		        System.out.print("\n");
		        m=m+2;
		    }
		    
		break;
	    
		case 4:
			System.out.println("Bye.");
			
			break;
		
		}
		
		}while(choose!=4);
		
	    scanner.close();

	}
	
}


