package myjava.homework.part1;
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
@SuppressWarnings("unused")

public class GradeSystem {
    static ArrayList<StudentGrade> studentGrades;
     
    public static void main(String[] args) {
    
    	int n=0;
    	Scanner file = null;
    	
    	do{
    		studentGrades = new ArrayList<StudentGrade>();
    		
    		@SuppressWarnings("resource")
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.println("Please input file name:");
    		String filename = scanner.next();
    		System.out.println("Reading file - " + filename);
    		try{
    			file = new Scanner(new FileInputStream(("src/myjava/homework/part1/"+filename)));	
    			
    			String ID,name;
    			int score;
    			String text;
    			
    			while(file.hasNextLine()){
    				text = file.nextLine();
    				String[] line = text.split(" ");
    				ID = line[0];
    				IDlength(ID);
    				name = line[1];
    				namelength(name);
    				character(line[2]);
    				score = Integer.valueOf(line[2]);
    				scorerange(score);
    				StudentGrade studentgrade = new StudentGrade(ID,name,score);
    				studentGrades.add(studentgrade);
    			}
    			
    			System.out.println("ID          NAME           GradePoint");
    			for(int i=0;i<studentGrades.size();i++) {
    				StudentGrade c = studentGrades.get(i);
    				System.out.printf("%-12s%-15s%s\n",c.changeID,c.changename,c.gradePoint);
    	        }
    			System.out.println("Total number of students: " + studentGrades.size());
    			
    			n=1; /*¥¿½Tµ²§ô*/
    		}
    		catch (FileNotFoundException e)
    		{
    			System.out.println("File not found.");
    		}
    		catch(IllegalLengthException e)
    		{
    			System.out.println(e.show());			
    		}
    		catch(IllegalCharacterException e)
    		{
    			System.out.println(e.show());			
    		}
    		catch(OutOfRangeException e)
    		{
    			System.out.println(e.show());			
    		}
    		
    		file.close();
    		
    	}while(n!=1);
    	
    }
    
	public static void IDlength(String ID) throws IllegalLengthException{
		if(ID.length()!=10){
			throw new IllegalLengthException(0);
		}
	}
	
	public static void namelength(String name) throws IllegalLengthException{
		if(name.length()<2 || name.length()>15){
			throw new IllegalLengthException(1);
		}
	}
	
	public static void character(String name) throws IllegalCharacterException{
		char[] a = name.toCharArray();
		
		for(int i=0;i<name.length();i++){
			if(a[i]>57 || a[i]<48){
				throw new IllegalCharacterException();
			}
		}

	}
	
	public static void scorerange(int score) throws OutOfRangeException{
		if(score<0 || score>100){
			throw new OutOfRangeException();
		}
	}
	
}