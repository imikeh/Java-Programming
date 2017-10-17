package myjava.homework.part1;

public class StudentGrade {
    public enum GradePoint{A_PLUS, A, A_MINUS, B_PLUS, B, B_MINUS, C_PLUS, C, C_MINUS, D, E, F, X}

    public String changeID;
    public String changename;
    public int changescore;
    public GradePoint gradePoint;
    
    // You can change the arguments of constructor to anything you want.
    public StudentGrade(String[] data) throws IllegalCharacterException, IllegalLengthException, OutOfRangeException{
        // Check if data input is valid here.
    }
    
    public StudentGrade(String ID,String name,int score){
    	changeID = ID;
    	changename = name;
    	changescore = score;
    	
    	if(score<101&&score>89){
    		gradePoint=GradePoint.valueOf("A_PLUS");
    	}
		if(score<90&&score>84){
			gradePoint=GradePoint.valueOf("A");
		}
		if(score<85&&score>79){
			gradePoint=GradePoint.valueOf("A_MINUS");
		}
		if(score<80&&score>76){
			gradePoint=GradePoint.valueOf("B_PLUS");
		}
		if(score<77&&score>72){
			gradePoint=GradePoint.valueOf("B");
		}
		if(score<73&&score>69){
			gradePoint=GradePoint.valueOf("B_MINUS");
		}
		if(score<70&&score>66){
			gradePoint=GradePoint.valueOf("C_PLUS");
		}
		if(score<67&&score>62){
			gradePoint=GradePoint.valueOf("C");
		}
		if(score<63&&score>59){
			gradePoint=GradePoint.valueOf("C_MINUS");
		}
		if(score<60&&score>49){
			gradePoint=GradePoint.valueOf("D");
		}
		if(score<50&&score>39){
			gradePoint=GradePoint.valueOf("E");
		}
		if(score<40&&score>0){
			gradePoint=GradePoint.valueOf("F");
		}
		if(score==0){
			gradePoint=GradePoint.valueOf("X");
		}
	
    }
    
}
