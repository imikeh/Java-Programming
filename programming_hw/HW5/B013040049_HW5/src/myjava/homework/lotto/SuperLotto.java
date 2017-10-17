package myjava.homework.lotto;

public class SuperLotto extends Lotto {
	
	public SuperLotto(int numOfWinNum,int maxOfNum){
		super(numOfWinNum,maxOfNum);
		super.generate();
		super.show(null);
		super.numOfWinNum = 1;
		super.maxOfNum = 8;
		super.generate();
		super.show(null);
		
	}

}
