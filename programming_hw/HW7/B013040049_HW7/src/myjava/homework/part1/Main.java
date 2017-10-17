package myjava.homework.part1;

import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int flag=0;
		
		Scanner choose  = new Scanner(System.in);
		
		while(flag!=3){
		
		System.out.println("1) Serialize object to file");
		System.out.println("2) Deserialize from the file to object");
		System.out.println("3) Exit");
		System.out.println("Choose:");
		
		flag = choose.nextInt();
		choose.nextLine();

		String filename,word,description;
		
		if(flag == 1){
			System.out.println("Please input file name:");
			filename = choose.nextLine();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			System.out.println("Input the word:");
			word = choose.nextLine();
			System.out.println("Input the description");
			description = choose.nextLine();
			DictionaryRecord record = new DictionaryRecord(word,description);
			oos.writeObject(record);
			oos.close();
		}
		else if(flag == 2){
			System.out.println("Please input file name:");
			filename = choose.nextLine();
			System.out.println("Reading file - " + filename);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			DictionaryRecord read = (DictionaryRecord) ois.readObject();
			System.out.println(read.word);
			System.out.println(read.description);
			ois.close();
		}
		else if(flag == 3){
			break;
		}
        
		System.out.println("");
        	
		}
		choose.close();
		}
	}


