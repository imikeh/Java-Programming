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
		this.start = start; /*�}�l�`�I*/
		this.finish = finish; /*�����`�I*/
		this.num = num; /*�`�I�ƥ�*/
		
		visited = new int[num];
		
		for(int i = 0;i<num;i++){
			visited[i] = 0;
		}
	}
	
	public void DFS(Node nowvalue, ArrayList<Integer> ans) {
		if (nowvalue.data == finish) { /*�P�_�O�_�򵲧��I�@�ˤF*/
			ans.add(finish);
			flag = 1; 
		}
		if(visited[nowvalue.data]==0 && flag != 1) { 
			visited[nowvalue.data] = 1; /*���L*/
			ans.add(nowvalue.data);
			for(Iterator<Node> IT = link[nowvalue.data].iterator(); IT.hasNext();){ /*��hasnext�S���F��ɭԸ��X�j��*/
				DFS(IT.next(),ans); /*���j*/
			}
			if(flag != 1) {
				ans.remove(ans.size()-1);
			}
		}
	}
	
}