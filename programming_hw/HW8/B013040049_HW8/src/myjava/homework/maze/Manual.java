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
		while(nowvalue != finish){ /*��n�h���ȸ򵲧��Ȥ@�ˮɸ��X*/
			System.out.printf("You are in node " + nowvalue + ". You can go ");
			for(Iterator<Node> IT = link[nowvalue].iterator(); IT.hasNext();){
				Node node = IT.next();
				System.out.print("'" + node.data + "' ");
			}
			System.out.println(".");
			System.out.printf("Please enter next node:");
			int prev = nowvalue; /*����ثe�Ȧs�_��*/
			nowvalue = next.nextInt(); /*Ū�U�@�ӭn�h���a��*/
			for(Iterator<Node> IT = link[prev].iterator(); IT.hasNext();){ /*�T�w���o�Ӧa��*/
				Node node = IT.next();
				if (nowvalue == node.data){
					flag=1;
				}
			}
			if(flag==0){
				System.out.println("You can't go " + "'" + nowvalue + "'"); /*�S���o�Ӧa��άO�^�Y*/
				nowvalue = prev; /*����@��*/
			}
		}
		System.out.println("Arrival!!");
		next.close();
	}
	

}
