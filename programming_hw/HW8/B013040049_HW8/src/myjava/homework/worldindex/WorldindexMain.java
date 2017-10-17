package myjava.homework.worldindex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.LinkedHashMap;

public class WorldindexMain{

		public static void main(String[] args){
			int i;
			
			Map<String, ArrayList<Integer>> table = new LinkedHashMap<String,ArrayList<Integer>>();
			
			TextFile file =new TextFile("src\\myjava\\homework\\worldindex\\record","\\W+");         
			
			for(i=0;i<file.size();i++){
				
				String key = file.get(i); /*Ū�r��i��*/
				
				if(table.containsKey(key)){ /*containskey�i�H�ΨӤ���O�_���o�Ӧr�A���Ƥ~�i��*/	
					ArrayList<Integer> list1 = new ArrayList<Integer>();
					list1 = (ArrayList<Integer>)table.get(key); /*�qtable���⭫�ƪ��r�s�X��*/
					list1.add(i+1); /*��m+1�A�]����l��m�O0*/
					table.put(key, list1);		
				}
				else{
					ArrayList<Integer> list1 = new ArrayList<Integer>();
					list1.add(i+1); /*��m+1�A�]����l��m�O0*/
					table.put(key, list1);
				}
							
			}

			Map<String, ArrayList<Integer>> treeMap = new TreeMap<String, ArrayList<Integer>>(table);
			System.out.println("Numbers of the word: ");
			printMap(treeMap);
			
			System.out.println("");
			System.out.println("Map of word location: " + table);
			System.out.println("");
			
			System.out.printf("Ordered location, words: {");
			for(i=0;i<file.size();i++){
				System.out.printf(i+1 + "=" + file.get(i));
				if(i==file.size()-1)
					break;
				System.out.printf(", ");
			}
			System.out.printf("}\n");
	    }
		
		public static void printMap(Map<String, ArrayList<Integer>> map){
			for (Iterator<Entry<String, ArrayList<Integer>>> iterator = map.entrySet()
					.iterator(); iterator.hasNext();) {
				Entry<String, ArrayList<Integer>> entry = iterator.next();
				ArrayList<Integer> list2 = map.get(entry.getKey());
			    int time = list2.size(); /*�X�{���ƶq*/
				System.out.printf("%-20s:%d\n","'" + entry.getKey() + "'", time);
			}
		}
	}
