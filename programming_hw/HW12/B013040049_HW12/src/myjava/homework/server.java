package myjava.homework;

import java.net.*;
import java.io.*;
import java.util.*;

class ServerReceiver extends Thread{
	     
		private DataInputStream DataIn;
		private DataOutputStream DataOut;
		private String username, groupName;
		private int group;
	    
		private Socket s;
	
		public ServerReceiver(Socket s) throws IOException{
			this.s = s;
			BufferedInputStream BIS = new BufferedInputStream(s.getInputStream());
			DataIn = new DataInputStream(BIS);
			BufferedOutputStream BOS = new BufferedOutputStream(s.getOutputStream());
			DataOut = new DataOutputStream(BOS);
		}
		
		private static HashMap<String, DataOutputStream> outputstreamMap = new HashMap<>();

		public static void removeusernamefromoutputstreamMap(String username){ /*把成員從outputstreamMap移除*/
			outputstreamMap.remove(username);
		}
		
		public static void broadcastmessage(String groupName, String msg)throws IOException{ /*廣播訊息到client*/
				String nowgroup; 
				Iterator<String> IT = groupmember.keySet().iterator();
				
				while(IT.hasNext()){
					nowgroup = IT.next();
					if(nowgroup.equals(groupName)){
						Iterator<String> User = groupmember.get(nowgroup).iterator();
						while(User.hasNext()){
							String TheUser = User.next();
							outputstreamMap.get(TheUser).writeUTF(msg);
							outputstreamMap.get(TheUser).flush();
						}
					}
				}				
		}

		private static HashMap<String, ArrayList<String>> groupmember = new HashMap<>(); /*建立group和成員所組成的map*/

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
			HashMap<String, ArrayList<String>> checkgroupmap = new HashMap<String, ArrayList<String>>();
			Iterator<String> IT = groupmember.keySet().iterator();
			String nowgroup;
			while(IT.hasNext()){
				if(!groupmember.get(nowgroup = IT.next()).isEmpty()){
					checkgroupmap.put(nowgroup, groupmember.get(nowgroup));
				}
			}
			groupmember = checkgroupmap;
		}		
		

		@Override public void run(){ /*override thread method run*/
			try{

				if(!peopleingroupmap().isEmpty()){
					String servergroup = "Groups in Server : \n";
					Iterator<String> IT = peopleingroupmap().keySet().iterator();
					while(IT.hasNext()){
						servergroup = servergroup + IT.next() + "\n";
					}
					DataOut.writeUTF(servergroup);
					DataOut.flush();
				}
				else{
					DataOut.writeUTF("There is no group in server now");
					DataOut.flush();
				}			
				
				username = DataIn.readUTF();
				System.out.println("user name: " + username);
				group = DataIn.readInt();
				groupName = DataIn.readUTF();
				
				switch(group){
				case 1:
					addnewgroup(groupName);
					addusernametogroup(groupName, username);
					System.out.println("New group : " + groupName + " created");
					//output.write(("Create group " + groupName + " successed!").getBytes());
					break;
				case 2:
					broadcastmessage(groupName, username + " joined the group" + "\n");
					addusernametogroup(groupName, username);
					//output.write(("join group \"" + groupName + "\" successed").getBytes());
					break;
				}				

				Iterator<String> IT = peopleingroupmap().get(groupName).iterator(); /*顯示目前有那些人在這群組*/
				String memberinroom = groupName + " now has:\n";
				while(IT.hasNext()){
					memberinroom = memberinroom + IT.next() + "\n";
				}
				DataOut.writeUTF(memberinroom);
				DataOut.flush();
				
				outputstreamMap.put(username, DataOut);
				 
				while(true){
					String receivemessage = DataIn.readUTF();
					if(receivemessage.equalsIgnoreCase("EXIT")){ /*如果訊息是EXIT，準備關閉連線*/
						s.shutdownOutput();
						removeusernamefromoutputstreamMap(username);
						removeusernamefromgroup(groupName, username);
						broadcastmessage(groupName, username + " has leave." + "\n"); /*廣播訊息到群組告知離開*/
						check();
						System.out.println(username + " EXIT" + " , Now hava " + peopleingroupmap().get(groupName).size() + " in " + groupName); /*server顯示誰離開*/
						break;
					}
					else{
						broadcastmessage(groupName, username + " says:" + receivemessage + "\n"); /*廣播訊息到同群組*/
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
	    		}
			}
			catch(IOException e){
			System.out.println("Disconnection");
			System.exit(-1);
			}
		}
		
}

