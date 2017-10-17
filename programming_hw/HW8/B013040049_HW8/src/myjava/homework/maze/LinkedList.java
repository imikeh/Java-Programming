package myjava.homework.maze;

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList {
	
	ArrayList<Node> mylist;

	public LinkedList() {
		mylist = new ArrayList<Node>();
	}

	public void add(int value) { /*所連接的下一個點*/
		Node next = new Node(value);
		mylist.add(next);
	}

	public Iterator<Node> iterator() {
		Iterator<Node> iterator = mylist.iterator();
		return iterator;
	}

}