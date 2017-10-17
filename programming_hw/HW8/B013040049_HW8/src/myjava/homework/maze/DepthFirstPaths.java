package myjava.homework.maze;

import java.util.ArrayList;
import java.util.Iterator;

public class DepthFirstPaths {
	LinkedList[] link;
	int start;
	int finish;
	int num;
	int flag;
	int[] visited;

	public DepthFirstPaths(LinkedList[] link, int num, int start, int finish) {
		this.link = link;
		this.start = start; /*開始節點*/
		this.finish = finish; /*結束節點*/
		this.num = num; /*節點數目*/
		
		visited = new int[num];
		
		for(int i = 0;i<num;i++){
			visited[i] = 0;
		}
	}
	
	public void DFS(Node nowvalue, ArrayList<Integer> ans) {
		if (nowvalue.data == finish) { /*判斷是否跟結束點一樣了*/
			ans.add(finish);
			flag = 1; 
		}
		if(visited[nowvalue.data]==0 && flag != 1) { 
			visited[nowvalue.data] = 1; /*走過*/
			ans.add(nowvalue.data);
			for(Iterator<Node> IT = link[nowvalue.data].iterator(); IT.hasNext();){ /*當hasnext沒有東西時候跳出迴圈*/
				DFS(IT.next(),ans); /*遞迴*/
			}
			if(flag != 1) {
				ans.remove(ans.size()-1);
			}
		}
	}
	
}