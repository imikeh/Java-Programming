package myjava.homework.lotto;

public class BigLotto extends Lotto {
	
	public BigLotto(int numOfWinNum,int maxOfNum){
		super(numOfWinNum,maxOfNum); /*��J6,49*/
		super.generate();
		super.show(null); 
	}

}
