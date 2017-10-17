package myjava.homework.maze;

import java.util.Iterator;
import java.util.Scanner;

public class Manual {
	LinkedList[] link;
	int start;
	int finish;
	int nowvalue;
	int flag = 0;
	
	Scanner next = new Scanner(System.in);
	
	public Manual(int start, int finish, LinkedList[] link) {
		this.nowvalue = start;
		this.finish = finish;
		this.link = link;
	}
	
	void print(){
		while(nowvalue != finish){ /*當要去的值跟結束值一樣時跳出*/
			System.out.printf("You are in node " + nowvalue + ". You can go ");
			for(Iterator<Node> IT = link[nowvalue].iterator(); IT.hasNext();){
				Node node = IT.next();
				System.out.print("'" + node.data + "' ");
			}
			System.out.println(".");
			System.out.printf("Please enter next node:");
			int prev = nowvalue; /*先把目前值存起來*/
			nowvalue = next.nextInt(); /*讀下一個要去的地方*/
			for(Iterator<Node> IT = link[prev].iterator(); IT.hasNext();){ /*確定有這個地方*/
				Node node = IT.next();
				if (nowvalue == node.data){
					flag=1;
				}
			}
			if(flag==0){
				System.out.println("You can't go " + "'" + nowvalue + "'"); /*沒有這個地方或是回頭*/
				nowvalue = prev; /*重選一次*/
			}
		}
		System.out.println("Arrival!!");
		next.close();
	}
	

}
