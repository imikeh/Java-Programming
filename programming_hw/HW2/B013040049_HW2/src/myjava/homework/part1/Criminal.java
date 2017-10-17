package myjava.homework.part1;

public class Criminal {
	
	private String name;
	private boolean gender;
	private int age;
	private String id;

	public Criminal() {
	
	};
	
	public Criminal(String name){
		
	};
	
	public Criminal(String name,boolean gender,int age,String id){
		
	};
	
	void setName(String name){
		this.name = name;
	};
	
	void setData(String name,boolean gender,int age,String id){
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.id = id;
	};
	
	String getName(){
		return name;
	};
	
	boolean getGender(){
		return gender;
	};
	
	int getAge(){
		return age;
	};
	
	String getID(){
		return id;
	};
	
	}

	
	

