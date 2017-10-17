package myjava.homework.lotto;

public class Draw extends Lotto{
	
	public Draw(int winnumber, int range){
	 super(winnumber,range);
	 super.generate();
	 super.show(null);
	}
}