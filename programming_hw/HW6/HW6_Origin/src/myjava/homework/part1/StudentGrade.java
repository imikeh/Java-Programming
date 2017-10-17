package myjava.homework.part1;

public class StudentGrade {
    public enum GradePoint{A_PLUS, A, A_MINUS, B_PLUS, B, B_MINUS, C_PLUS, C, C_MINUS, D, E, F, X}

    public String id;
    public String name;
    public int score;
    public GradePoint gradePoint;
    
    // You can change the arguments of constructor to anything you want.
    public StudentGrade(String[] data) throws IllegalCharacterException, IllegalLengthException, OutOfRangeException{
        // Check if data input is valid here.
        
    }
}
