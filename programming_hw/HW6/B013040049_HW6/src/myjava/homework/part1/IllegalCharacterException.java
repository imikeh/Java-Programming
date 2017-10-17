package myjava.homework.part1;

@SuppressWarnings("serial")
public class IllegalCharacterException extends Exception{

	private String message;
	
	IllegalCharacterException (){
		message="Score contains invalid characters.";
	}  
	
	public String show(){
		return message;
	}

}
