package myjava.homework.part1;

@SuppressWarnings("serial")
public class IllegalLengthException extends Exception{
	
	@SuppressWarnings("unused")
	private int value;
	private String message;
	
	public IllegalLengthException (int value){
		this.value = value;
        if(value==0){
        	message="Length of student id must be 10.";
        }
        if(value==1){
        	message="Length of student name must be between 2 and 15.";
        }
	}
	
	public String show(){
	         return message;
	}

}

