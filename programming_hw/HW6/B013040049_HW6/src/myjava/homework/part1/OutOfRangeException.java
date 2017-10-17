package myjava.homework.part1;

@SuppressWarnings("serial")
public class OutOfRangeException extends Exception{
	
	private String message;
	
	OutOfRangeException (){
		message="Score must be between 0 and 100.";
	}  
	
	public String show(){    
		return message;
	}
}

