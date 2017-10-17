package myjava.homework.part1;

import java.util.Scanner;

public class Blacklist {

	public static void main(String[] args) {
		
		int type=0;
		int cc=0,ccc=0;
		
		String a=null,d=null,e=null;
		boolean b;
		int c=0;
		
		String name=null;
		boolean gender=false;
		int age=0;
		String id=null;
		
		Criminal one = new Criminal();
		Criminal two = new Criminal(name);
		Criminal three = new Criminal(name,gender,age,id);
		
	    two.setName("Ballchinian");
		three.setData("Scofield",true,25,"C010240049");
		
		Scanner scanner = new Scanner(System.in);
		
            do{
            	
			System.out.println("Type 1 means looking for some one.");
			System.out.println("Type 2 means change someone's detail.");
			System.out.print("Input:");
			type = scanner.nextInt();
			
			if(type ==1){
				System.out.println("Which one you want to check?(1,2,3)");
				System.out.print("Input:");
				cc = scanner.nextInt();
				
				if(cc==1){
					System.out.println("First suspect is:" + one.getName());
					System.out.println("He/She's age is:" + one.getAge());
					System.out.println("Social ID:" + one.getID());
					System.out.printf("Gender:");
					
					if(one.getGender()==true){
						System.out.println("male");
					}
					else{
						System.out.println("female");
					}
					
				}
				else if(cc==2){
					System.out.println("First suspect is:" + two.getName());
					System.out.println("He/She's age is:" + two.getAge());
					System.out.println("Social ID:" + two.getID());
                    System.out.printf("Gender:");
					
					if(two.getGender()==true){
						System.out.println("male");
					}
					else{
						System.out.println("female");
					}
					
				}
				else{
					System.out.println("First suspect is:" + three.getName());
					System.out.println("He/She's age is:" + three.getAge());
					System.out.println("Social ID:" + three.getID());
                    System.out.printf("Gender:");
					
					if(three.getGender()==true){
						System.out.println("male");
					}
					else{
						System.out.println("female");
					}
					
				}			
			}
			else if(type ==2){
				System.out.println("Which one you want to change?(1,2,3)");
				System.out.print("Input:");
				ccc = scanner.nextInt();
				if(ccc==1){ 
					System.out.printf("Name:");
			        a = scanner.next();
		            System.out.print("Gender(male/female):");
		            e = scanner.next();
		            if(e=="male"){
		            	b = true;
		            }
		            else{
		            	b = false;
		            }
					System.out.print("Age:");
					c = scanner.nextInt();
					System.out.print("ID:");
					d = scanner.next();
					one.setData(a,b,c,d);
				}
				else if(ccc==2){
					System.out.printf("Name:");
			        a = scanner.next();
		            System.out.print("Gender(male/female):");
		            e = scanner.next();
		            if(e=="male"){
		            	b = true;
		            }
		            else{
		            	b = false;
		            }
					System.out.print("Age:");
					c = scanner.nextInt();
					System.out.print("ID:");
					d = scanner.next();
					two.setData(a,b,c,d);				
				}
				else{
					System.out.printf("Name:");
			        a = scanner.next();
		            System.out.print("Gender(male/female):");
		            e = scanner.next();
		            if(e=="male"){
		            	b = true;
		            }
		            else{
		            	b = false;
		            }
					System.out.print("Age:");
					c = scanner.nextInt();
					System.out.print("ID:");
					d = scanner.next();
					three.setData(a,b,c,d);
				}
				
				
			}
			
            }while(type!=0);
            scanner.close();
	}

	
        
	}

