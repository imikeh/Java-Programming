package myjava.homework;

import java.net.*;
import java.io.*;
import java.util.*;

class ServerReceiver extends Thread{
	
		private Socket s;
	
		public ServerReceiver(Socket s){
			this.s = s;
		}	
	
		
		@Override public void run(){ /*override thread method run*/
			try{
				InputStream input = s.getInputStream();
				OutputStream output = s.getOutputStream();
				
				int lengthfromclient; /*所接收訊息的長度*/
				byte[] bytefromclient = new byte[1000]; /*利用byte[]來接受訊息*/
				
				lengthfromclient = input.read(bytefromclient);		
				String username = new String(bytefromclient, 0, lengthfromclient); /*建立一個string username從一個subarray*/
				System.out.println("user name : " + username);

				if(!server.peopleingroupmap().isEmpty()){
					String servergroup = "Groups in Server : \n";
					Iterator<String> IT = server.peopleingroupmap().keySet().iterator();
					while(IT.hasNext()){
						servergroup = servergroup + IT.next() + "\n";
					}
					output.write(servergroup.getBytes());
				}
				else{
					output.write(("There is no group in server now").getBytes());
				}			
			
				lengthfromclient = input.read(bytefromclient);		
				int group = Integer.parseInt(new String(bytefromclient, 0, lengthfromclient));
				lengthfromclient = input.read(bytefromclient);		
				String groupName = new String(bytefromclient, 0, lengthfromclient);
				
				switch(group){
				case 1:
					server.addnewgroup(groupName);
					server.addusernametogroup(groupName, username);
					System.out.println("New group : " + groupName + " created");
					output.write(("Create group " + groupName + " successed!").getBytes());
					break;
				case 2:
					server.addusernametogroup(groupName, username);
					output.write(("join group \"" + groupName + "\" successed").getBytes());
					break;
				}				

				server.setoutputstreamMap(username, this.s.getOutputStream()); /*把成員加到outputstreammap*/

				Iterator<String> IT = server.peopleingroupmap().get(groupName).iterator(); /*顯示目前有那些人在這群組*/
				String memberinroom = groupName + " now has:\n";
				while(IT.hasNext()){
					memberinroom = memberinroom + IT.next() + "\n";
				}
				output.write(memberinroom.getBytes());			

				String receivemessage; 
				while(true){
					lengthfromclient = input.read(bytefromclient); /*獲得訊息從成員*/
					if((receivemessage = new String(bytefromclient, 0, lengthfromclient)).equals("EXIT")){ /*如果訊息是EXIT，準備關閉連線*/
						s.shutdownOutput();
						server.removeusernamefromoutputstreamMap(username);
						server.removeusernamefromgroup(groupName, username);
						server.broadcastmessage(groupName, username + " has leave."); /*廣播訊息到群組告知離開*/
						server.check();
						System.out.println(username + " EXIT" + " , Now hava " + server.peopleingroupmap().get(groupName).size() + " in " + groupName); /*server顯示誰離開*/
						break;
					}
					else{
						server.broadcastmessage(groupName, username + " says:" + receivemessage); /*廣播訊息到同群組*/
					}
				}
			}
			catch(IOException e){
				System.out.println("Disconnection");
				System.exit(-1);
			}
		}
}

public class server{
		public static void main(String[] args)  throws IOException{	
			try{			
	    		int port;
	    		Scanner scn = new Scanner(System.in);
				
	    		System.out.print("Port:");
	    		port = scn.nextInt(); /*設定port*/
	    		System.out.println("Open Port " + port +" sucessfully");
    			System.out.println("Accept client");
    			System.out.printf("\n___START___\n\n");
	    		
	    		ServerSocket server = new ServerSocket(port); /*建立server socket*/

	    		while(true){				
	    			Socket socket = server.accept();
	    			ServerReceiver serverthread = new ServerReceiver(socket);
	    			serverthread.start();
	    			System.out.println("new one come in\n");
				
	    			Iterator<String> IT = peopleingroupmap().keySet().iterator(); /*檢查現在server有哪些group，有多少人*/
	    			String groupname;
	    			while(IT != null && IT.hasNext()){
	    				groupname = IT.next();
	    				System.out.println("Server : ");
	    				System.out.println("Now have " + peopleingroupmap().get(groupname).size() + " people in " + groupname);
	    			}
	    		}
			}
			catch(IOException e){
			System.out.println("Disconnection");
			System.exit(-1);
			}
		}
		
		private static Map<String, OutputStream> outputstreamMap = new HashMap<String, OutputStream>();
		
		public static void setoutputstreamMap(String userName, OutputStream outputStream){ /*把成員加入到outputstreamMap*/
			outputstreamMap.put(userName, outputStream);
		}

		public static void removeusernamefromoutputstreamMap(String username){ /*把成員從outputstreamMap移除*/
			outputstreamMap.remove(username);
		}
		
		public static void broadcastmessage(String groupName, String msg){ /*廣播訊息到client*/
			try{
				String nowgroup; 
				Iterator<String> IT = groupmember.keySet().iterator();

				while(IT.hasNext()){ 
					if((nowgroup = IT.next()).equals(groupName)){ /*在groupmember中找尋所要廣播的group*/
						Iterator<String> it = groupmember.get(nowgroup).iterator(); /*找到後準備廣播*/
						while(it.hasNext()){
							outputstreamMap.get(it.next()).write(msg.getBytes());
						}
					}
				}
			}
			catch(IOException e){
				System.out.println("Disconnection");
				System.exit(-1);
			}
		}

		private static Map<String, ArrayList<String>> groupmember = new HashMap<String, ArrayList<String>>(); /*建立group和成員所組成的map*/

		public static Map<String, ArrayList<String>> peopleingroupmap(){ /*為了要回傳groupmember來抓資料而已*/
			return groupmember;
		}
		
		public static void addnewgroup(String groupName){ /*新增group*/
			groupmember.put(groupName, new ArrayList<String>());
		}

		public static void addusernametogroup(String groupName, String username){ /*加入成員到指定的group*/
			groupmember.get(groupName).add(username);		
		}

		public static void removeusernamefromgroup(String groupName, String username){ /*把成員從特定的group移除*/
			groupmember.get(groupName).remove(username);
		}

		public static void check(){ /*當有人離開時候，檢查是否還有人在群組，若沒有人了則移除群組*/
			Map<String, ArrayList<String>> checkgroupmap = new HashMap<String, ArrayList<String>>();
			Iterator<String> IT = groupmember.keySet().iterator();
			String nowgroup;
			while(IT.hasNext()){
				if(!groupmember.get(nowgroup = IT.next()).isEmpty()){
					checkgroupmap.put(nowgroup, groupmember.get(nowgroup));
				}
			}
			groupmember = checkgroupmap;
		}
}

