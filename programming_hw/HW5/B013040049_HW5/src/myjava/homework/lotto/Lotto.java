package myjava.homework.lotto;

public class Lotto implements display, LuckyNumlist{
	
    private Object[] items;
    int numOfWinNum;
    int maxOfNum;
	
    public Lotto(int numOfWinNum,int maxOfNum){
    	this.numOfWinNum = numOfWinNum;
    	this.maxOfNum = maxOfNum;
    }
    
    public void generate(){
		generateWinNums();
	}
    
    private void generateWinNums(){
		items = new Object [numOfWinNum];
		items[0] = (int)(Math.random()*maxOfNum)+1;
		int i=0,j=1,k,same;

		for(i=1;i<numOfWinNum;i++){
			same = 0;
			k = (int)(Math.random()*maxOfNum)+1;
			for(j=0;j<i;j++){
				if(k==(int)items[j]){
					same = 1;
				}
			}
			if(same==0){
				items[i]=k;
			}
			else{
				i--;
				continue;
			}
		}
		
    }
    
    public Selector selector(){
		return new SequenceSelector();
	}
    
    private class SequenceSelector implements Selector{
    	private int i=0;
		public boolean end(){
			if(i == items.length){
				return false;
			}
			else{
				return true;
			}
		}
		public Object current(){
			return items[i];
		}
		public void next(){
			i++;
		}
	}
	
	public void show(Selector s){
		
		s = selector();
		
		while(s.end()!=false){	
		System.out.print(s.current() + " ");
		s.next();
		}
		System.out.printf("\n");
	}

}
