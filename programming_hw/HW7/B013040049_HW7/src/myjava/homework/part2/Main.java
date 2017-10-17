package myjava.homework.part2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import myjava.homework.part1.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<DictionaryRecord> dictionaryRecords = new ArrayList<DictionaryRecord>(); 
		Scanner choose = new Scanner(System.in);
		try
        {			
		    BufferedReader readdict = new BufferedReader(new FileReader("dict.txt"));
			
        	String word,description;
        	
        	word = readdict.readLine();
        	
        	while(word!=null){
        		DictionaryRecord record = new DictionaryRecord(word);
        		StringBuilder result  = new StringBuilder();
        		description = readdict.readLine();
        		
        		do{
        			result.append(description);
        			description = readdict.readLine();
        			if(description==null || "".equals(description)){
        				break;
        			}
        		}while(!(description.equals("\r\n")));
        		
        		record.setDescription(result.toString());
        		dictionaryRecords.add(record);
        		word = readdict.readLine();
        	}
        	readdict.close();
        	
        	while(true){
        		System.out.printf("What word do you want : ");
        		word = choose.nextLine();
        		
        		int flag = 0,i,ans=0;
        		
        		for(i=0;i<dictionaryRecords.size();i++){
        			if(dictionaryRecords.get(i).word.equals(word)){
        				flag = 1;
                        ans = i;
        				break;
        			}
        		}
        		
        		if(flag==1){
        			System.out.println(dictionaryRecords.get(ans).word + " : " + dictionaryRecords.get(ans).description);
        		}
        		if(flag==0){
        			System.out.println(word + " : " + "Not Found!");
        		}
        	}
        }
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found !");
		}
		catch(IOException e)
		{
			System.out.println("Error Read !");
		}

	}

}
