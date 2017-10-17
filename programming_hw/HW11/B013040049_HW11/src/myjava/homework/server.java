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
				
				int lengthfromclient; /*�ұ����T��������*/
				byte[] bytefromclient = new byte[1000]; /*�Q��byte[]�ӱ����T��*/
				
				lengthfromclient = input.read(bytefromclient);		
				String username = new String(bytefromclient, 0, lengthfromclient); /*�إߤ@��string username�q�@��subarray*/
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

				server.setoutputstreamMap(username, this.s.getOutputStream()); /*�⦨���[��outputstreammap*/

				Iterator<String> IT = server.peopleingroupmap().get(groupName).iterator(); /*��ܥثe�����ǤH�b�o�s��*/
				String memberinroom = groupName + " now has:\n";
				while(IT.hasNext()){
					memberinroom = memberinroom + IT.next() + "\n";
				}
				output.write(memberinroom.getBytes());			

				String receivemessage; 
				while(true){
					lengthfromclient = input.read(bytefromclient); /*��o�T���q����*/
					if((receivemessage = new String(bytefromclient, 0, lengthfromclient)).equals("EXIT")){ /*�p�G�T���OEXIT�A�ǳ������s�u*/
						s.shutdownOutput();
						server.removeusernamefromoutputstreamMap(username);
						server.removeusernamefromgroup(groupName, username);
						server.broadcastmessage(groupName, username + " has leave."); /*�s���T����s�էi�����}*/
						server.check();
						System.out.println(username + " EXIT" + " , Now hava " + server.peopleingroupmap().get(groupName).size() + " in " + groupName); /*server��ܽ����}*/
						break;
					}
					else{
						server.broadcastmessage(groupName, username + " says:" + receivemessage); /*�s���T����P�s��*/
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
				
	    			Iterator<String> IT = peopleingroupmap().keySet().iterator(); /*�ˬd�{�bserver������group�A���h�֤H*/
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
		
		public static void setoutputstreamMap(String userName, OutputStream outputStream){ /*�⦨���[�J��outputstreamMap*/
			outputstreamMap.put(userName, outputStream);
		}

		public static void removeusernamefromoutputstreamMap(String username){ /*�⦨���qoutputstreamMap����*/
			outputstreamMap.remove(username);
		}
		
		public static void broadcastmessage(String groupName, String msg){ /*�s���T����client*/
			try{
				String nowgroup; 
				Iterator<String> IT = groupmember.keySet().iterator();

				while(IT.hasNext()){ 
					if((nowgroup = IT.next()).equals(groupName)){ /*�bgroupmember����M�ҭn�s����group*/
						Iterator<String> it = groupmember.get(nowgroup).iterator(); /*����ǳƼs��*/
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

		private static Map<String, ArrayList<String>> groupmember = new HashMap<String, ArrayList<String>>(); /*�إ�group�M�����Ҳզ���map*/

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

