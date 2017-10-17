package myjava.homework.part2;

public class UniqueCriminal {
	
	private String name =null;
	private boolean gender=false;
	private int age=0;
	private String id=null;
	private static UniqueCriminal bigBadGuy = null;
	
	private UniqueCriminal(String name,boolean gender,int age,String id){
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.id = id;
	};
	
	public static void printData(){
		System.out.println("The bad guy is:" + bigBadGuy.name);
		System.out.println("He/She's age is:" + bigBadGuy.age);
		System.out.println("Social ID:" + bigBadGuy.id);
		System.out.printf("Gender:");
		
		if(bigBadGuy.gender==true){
			System.out.println("male");
		}
		else{
			System.out.println("female");
		}
	};
	
	public static UniqueCriminal getInstance(String name,boolean gender,int age,String id){
		if(bigBadGuy == null) {
			bigBadGuy = new UniqueCriminal(name,gender,age,id);
	    }
		bigBadGuy.name = name;
		bigBadGuy.gender = gender;
		bigBadGuy.age = age;
		bigBadGuy.id = id;
		return bigBadGuy;
	}
	
}
