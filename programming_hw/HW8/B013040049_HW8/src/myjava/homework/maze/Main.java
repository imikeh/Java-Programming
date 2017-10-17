package myjava.homework.maze;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		int i;
		File read = new File("src/myjava/homework/maze/info2");
		Scanner file = new Scanner(read);
	
		Scanner input = new Scanner(System.in);
		
		int Nodesnumber = file.nextInt(); /*�`�@�`�I��*/
		
		LinkedList[] link = new LinkedList[Nodesnumber];

		int From, To; /*�q�ĤG��_��J���O�N��qX->Y*/
		
		while(file.hasNextInt()) {
			From = file.nextInt();
			To = file.nextInt();
			if(link[From] == null) {
				link[From] = new LinkedList();
			}
			link[From].add(To);
			if(link[To] == null) {
				link[To] = new LinkedList();
			}
			link[To].add(From);
		}
		file.close();

		System.out.printf("Please input start node:");
		int start = input.nextInt();
		System.out.printf("Please input finish node:");
		int finish = input.nextInt();
		System.out.printf("Please input a command (1.auto search path(using DFS) 2.manual search path):");
		
		int choice = input.nextInt(); /*DFS or ���*/
		
	    switch(choice){
	    case 1:
	    	DepthFirstPaths dfs = new DepthFirstPaths(link,Nodesnumber,start,finish);
			Node node = new Node(start); /*��Ȧs�inode*/
			
			ArrayList<Integer> ans = new ArrayList<Integer>();
			
			dfs.DFS(node,ans);
			
			if(dfs.flag == 1){
				System.out.printf("FIND PATH : ");
				for(i=0;i<ans.size()-1;i++){ /*�]��˼ƲĤG��*/
					System.out.printf(ans.get(i) + " -> ");
				}
				System.out.println(ans.get(ans.size()-1)); /*�̫�@�ӭ�*/
			}else{
				System.out.println("NOT FOUNDED PATH!!");
			}
			break;
	    
	    case 2:
			Manual man = new Manual(start, finish, link);
			man.print();
			break;
	    }
		
		
		input.close();
	}

}