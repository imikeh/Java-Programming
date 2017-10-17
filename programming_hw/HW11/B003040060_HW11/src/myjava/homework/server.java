package myjava.homework;

import java.net.*;
import java.io.*;
import java.util.*;

public class server{
	public static void main(String[] args){
		Scanner keyin = new Scanner(System.in);
		System.out.print("Port: "); 
		int portNum = keyin.nextInt(); 
		keyin.close();
		try{
			//�ǤJportNum�إ�ServerSocket����
			ServerSocket server = new ServerSocket(portNum);
			System.out.println("Open Port " + portNum + " sucessfully.");
			System.out.println("Waiting for client to accept connection...");
			//���ݳs�u���L�a�j��
			while(true){
				Socket client = server.accept();
				UserThread currentUser = new UserThread(client);
				currentUser.start();
				System.out.println("new one come in\n");
			}
		}
		catch(Exception ex){
			
		}
	}	
}

class UserThread extends Thread{
	private Socket UserSocket;
	private DataInputStream DataIn;
	private DataOutputStream DataOut;
	private String UserName, GroupName;
	private int GroupChoice;
	private static HashMap<String, ArrayList<String>> GroupMember = new HashMap<>();
	private static HashMap<String, DataOutputStream> UserDataOut = new HashMap<>();
	//�غc�l
	public UserThread(Socket client)throws IOException{
		UserSocket = client;
		//�إߦ�y����
		BufferedInputStream BIS = new BufferedInputStream(UserSocket.getInputStream());
		DataIn = new DataInputStream(BIS);
		BufferedOutputStream BOS = new BufferedOutputStream(UserSocket.getOutputStream());
		DataOut = new DataOutputStream(BOS);
	}
	//�s�ռs��
	private static void sendMsgs(String GroupName, String Msgs)throws IOException{
		String ThisGroup;
		//�إߩҦ�Group��Iterator
		Iterator<String> Group = GroupMember.keySet().iterator();
		while(Group.hasNext()){
			//�M��GroupName�ۦP���s��
			ThisGroup = Group.next();
			if(ThisGroup.equals(GroupName)){
				//�إ߸�Group�̩Ҧ�User��Iterator
				Iterator<String> User = GroupMember.get(ThisGroup).iterator();
				//��X�ܸ�group���C�@��client
				while(User.hasNext()){
					String TheUser = User.next();
					UserDataOut.get(TheUser).writeUTF(Msgs);
					UserDataOut.get(TheUser).flush();
				}
			}
		}
	}
	//��gThread���椺�e
	@Override public void run(){
		try{
			//Ū��client�ǤJ�����
			UserName = DataIn.readUTF();
			System.out.println("user name: " + UserName);
			
			//�P�_��e���L�s�եi�[�J�A�Ӧ^�Ǥ��P���T����client��
			if(GroupMember.isEmpty()){
				DataOut.writeUTF("No group can join.\n");
			}
			else{
				String GroupInServer = "Group: ";
				Iterator<String> group = GroupMember.keySet().iterator();
				while(group.hasNext()){
					GroupInServer += "\"" + group.next() + "\" ";
				}
				GroupInServer += "\n";
				DataOut.writeUTF(GroupInServer);
			}
			DataOut.flush();
			
			GroupChoice = DataIn.readInt();
			GroupName = DataIn.readUTF();
			String MemberInGroup = "";
			
			
			switch(GroupChoice)			
			{
			//GroupChoice=1�A�إ�group�åB�[�J
			case 1:
				System.out.println("New group: " + GroupName + " created");
				GroupMember.put(GroupName, new ArrayList<String>());
				GroupMember.get(GroupName).add(UserName);
				
				//�ǰe��Group�{���������Φ��\��T��client��
				MemberInGroup = "Create group " + GroupName +" sucessed!\n";
				MemberInGroup += "Member in group " + GroupName + ": ";
				Iterator<String> member1 = GroupMember.get(GroupName).iterator();
				while(member1.hasNext()){
					MemberInGroup += member1.next() + " ";
				}
				MemberInGroup += "\n";
				DataOut.writeUTF(MemberInGroup);
				DataOut.flush();
				break;
			//GroupChoice=2�A�[�J��J��group
			case 2:
				GroupMember.get(GroupName).add(UserName);
				
				//�ǰe��Group�{���������Φ��\��T��client��
				MemberInGroup = "Join group " + GroupName +" sucessed.\n";
				MemberInGroup += "Member in group " + GroupName + ": ";
				Iterator<String> member2 = GroupMember.get(GroupName).iterator();
				while(member2.hasNext()){
					MemberInGroup += member2.next() + " ";
				}
				MemberInGroup += "\n";
				DataOut.writeUTF(MemberInGroup);
				DataOut.flush();
				break;
			//��J�D1�D2����
			default:
				DataOut.writeUTF("Create or Join group " + GroupName +" failed.\n");
				DataOut.flush();
			}
			
			
			//�N��User�PDataOutputstream�[�JMap
			UserDataOut.put(UserName, DataOut);
			
			//Ū��client�ǤJ��Message�B�s�����Ҧb��Group���L�a�j��
			while(true){
				String Msgs = DataIn.readUTF();
				//�YMessage��EXIT
				if(Msgs.equalsIgnoreCase("EXIT")){
					//�q�NUser�qMap����
					sendMsgs(GroupName, UserName + " leaves the group.\n");
					GroupMember.get(GroupName).remove(UserName);
					UserDataOut.remove(UserName);
					System.out.println(UserName + " leaves the group " + GroupName + ".");
					//�Y�s�դw�LUser�A�R���s��
					if(GroupMember.get(GroupName).isEmpty()){
						GroupMember.remove(GroupName);
						System.out.println("Group " + GroupName +" has been remove.");
					}		
					break;
				}
				else
					//Message�DEXIT�A�h�s��User�ǻ���Message
					sendMsgs(GroupName, UserName + " says: " + Msgs + "\n");
			}
		}
		catch(Exception ex){
			
		}
	}
	
}
