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
			//傳入portNum建立ServerSocket物件
			ServerSocket server = new ServerSocket(portNum);
			System.out.println("Open Port " + portNum + " sucessfully.");
			System.out.println("Waiting for client to accept connection...");
			//等待連線的無窮迴圈
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
	//建構子
	public UserThread(Socket client)throws IOException{
		UserSocket = client;
		//建立串流物件
		BufferedInputStream BIS = new BufferedInputStream(UserSocket.getInputStream());
		DataIn = new DataInputStream(BIS);
		BufferedOutputStream BOS = new BufferedOutputStream(UserSocket.getOutputStream());
		DataOut = new DataOutputStream(BOS);
	}
	//群組廣播
	private static void sendMsgs(String GroupName, String Msgs)throws IOException{
		String ThisGroup;
		//建立所有Group的Iterator
		Iterator<String> Group = GroupMember.keySet().iterator();
		while(Group.hasNext()){
			//尋找GroupName相同的群組
			ThisGroup = Group.next();
			if(ThisGroup.equals(GroupName)){
				//建立該Group裡所有User的Iterator
				Iterator<String> User = GroupMember.get(ThisGroup).iterator();
				//輸出至該group的每一個client
				while(User.hasNext()){
					String TheUser = User.next();
					UserDataOut.get(TheUser).writeUTF(Msgs);
					UserDataOut.get(TheUser).flush();
				}
			}
		}
	}
	//改寫Thread執行內容
	@Override public void run(){
		try{
			//讀取client傳入的資料
			UserName = DataIn.readUTF();
			System.out.println("user name: " + UserName);
			
			//判斷當前有無群組可加入，而回傳不同的訊息給client端
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
			//GroupChoice=1，建立group並且加入
			case 1:
				System.out.println("New group: " + GroupName + " created");
				GroupMember.put(GroupName, new ArrayList<String>());
				GroupMember.get(GroupName).add(UserName);
				
				//傳送該Group現有的成員及成功資訊給client端
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
			//GroupChoice=2，加入輸入的group
			case 2:
				GroupMember.get(GroupName).add(UserName);
				
				//傳送該Group現有的成員及成功資訊給client端
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
			//輸入非1非2之值
			default:
				DataOut.writeUTF("Create or Join group " + GroupName +" failed.\n");
				DataOut.flush();
			}
			
			
			//將該User與DataOutputstream加入Map
			UserDataOut.put(UserName, DataOut);
			
			//讀取client傳入的Message且廣播給所在的Group的無窮迴圈
			while(true){
				String Msgs = DataIn.readUTF();
				//若Message為EXIT
				if(Msgs.equalsIgnoreCase("EXIT")){
					//從將User從Map移除
					sendMsgs(GroupName, UserName + " leaves the group.\n");
					GroupMember.get(GroupName).remove(UserName);
					UserDataOut.remove(UserName);
					System.out.println(UserName + " leaves the group " + GroupName + ".");
					//若群組已無User，刪除群組
					if(GroupMember.get(GroupName).isEmpty()){
						GroupMember.remove(GroupName);
						System.out.println("Group " + GroupName +" has been remove.");
					}		
					break;
				}
				else
					//Message非EXIT，則廣播User傳遞的Message
					sendMsgs(GroupName, UserName + " says: " + Msgs + "\n");
			}
		}
		catch(Exception ex){
			
		}
	}
	
}
