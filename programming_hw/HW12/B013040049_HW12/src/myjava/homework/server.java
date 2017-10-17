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

		public static void removeusernamefromoutputstreamMap(String username){ /*�⦨���qoutputstreamMap����*/
			outputstreamMap.remove(username);
		}
		
		public static void broadcastmessage(String groupName, String msg)throws IOException{ /*�s���T����client*/
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

		private static HashMap<String, ArrayList<String>> groupmember = new HashMap<>(); /*�إ�group�M�����Ҳզ���map*/

		public static Map<String, ArrayList<String>> peopleingroupmap(){ /*���F�n�^��groupmember�ӧ��ƦӤw*/
			return groupmember;
		}
		
		public static void addnewgroup(String groupName){ /*�s�Wgroup*/
			groupmember.put(groupName, new ArrayList<String>());
		}

		public static void addusernametogroup(String groupName, String username){ /*�[�J��������w��group*/
			groupmember.get(groupName).add(username);		
		}

		public static void removeusernamefromgroup(String groupName, String username){ /*�⦨���q�S�w��group����*/
			groupmember.get(groupName).remove(username);
		}

		public static void check(){ /*���H���}�ɭԡA�ˬd�O�_�٦��H�b�s�աA�Y�S���H�F�h�����s��*/
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

				Iterator<String> IT = peopleingroupmap().get(groupName).iterator(); /*��ܥثe�����ǤH�b�o�s��*/
				String memberinroom = groupName + " now has:\n";
				while(IT.hasNext()){
					memberinroom = memberinroom + IT.next() + "\n";
				}
				DataOut.writeUTF(memberinroom);
				DataOut.flush();
				
				outputstreamMap.put(username, DataOut);
				 
				while(true){
					String receivemessage = DataIn.readUTF();
					if(receivemessage.equalsIgnoreCase("EXIT")){ /*�p�G�T���OEXIT�A�ǳ������s�u*/
						s.shutdownOutput();
						removeusernamefromoutputstreamMap(username);
						removeusernamefromgroup(groupName, username);
						broadcastmessage(groupName, username + " has leave." + "\n"); /*�s���T����s�էi�����}*/
						check();
						System.out.println(username + " EXIT" + " , Now hava " + peopleingroupmap().get(groupName).size() + " in " + groupName); /*server��ܽ����}*/
						break;
					}
					else{
						broadcastmessage(groupName, username + " says:" + receivemessage + "\n"); /*�s���T����P�s��*/
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
	    		port = scn.nextInt(); /*�]�wport*/
	    		System.out.println("Open Port " + port +" sucessfully");
    			System.out.println("Accept client");
    			System.out.printf("\n___START___\n\n");
	    		
	    		ServerSocket server = new ServerSocket(port); /*�إ�server socket*/

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

