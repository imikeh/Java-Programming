package myjava.homework;

import java.net.*;
import java.io.*;
import java.util.*;


public class client{

	public static void main (String[] args) throws Exception{
		Scanner keyin = new Scanner(System.in);
		System.out.print("Host: ");
		String IP= keyin.next();
		System.out.print("Port: ");
		int portNum = keyin.nextInt();
		//傳入IP, portNum建立Socket
		Socket clientSocket = new Socket(IP, portNum);
		//傳入clientSocket執行chatUser方法
		chatUser(clientSocket);

		keyin.close();
	}
	//用於接收Server端資料的Thread
	static class clientReceive extends Thread{
		private Socket UserSocket;
		public clientReceive(Socket socket){
			UserSocket = socket;
		}
		//改寫Thread的執行方法
		@Override public void run(){
			try{
				BufferedInputStream BIS = new BufferedInputStream(UserSocket.getInputStream());
				DataInputStream DataIn = new DataInputStream(BIS);
				while(true){
					System.out.print(DataIn.readUTF());
				}
			}
			catch(Exception ex){
				
			}
		}
	}
	private static void chatUser(Socket UserSocket) throws IOException{	
		BufferedReader BufferReadIn = new BufferedReader(new InputStreamReader(System.in));
		Scanner keyin = new Scanner(System.in);
		
		//建立資料串流
		BufferedInputStream BIS = new BufferedInputStream(UserSocket.getInputStream());
		DataInputStream DataIn = new DataInputStream(BIS);
		BufferedOutputStream BOS = new BufferedOutputStream(UserSocket.getOutputStream());
		DataOutputStream DataOut = new DataOutputStream(BOS); 
		
		//以下傳遞資料給Server
		System.out.println("Input user name: ");
		DataOut.writeUTF(BufferReadIn.readLine());
		DataOut.flush();
		
		//接收Server傳入的資料，印出當前現有group
		System.out.print(DataIn.readUTF());
		
		System.out.println("1.Add a new group.   2.Join a specific group.");
		DataOut.writeInt(keyin.nextInt());
		DataOut.flush();
		System.out.println("Input group name: ");
		DataOut.writeUTF(BufferReadIn.readLine());
		DataOut.flush();
		
		//接收Server傳入資料，印出所選之Group的成員
		System.out.print(DataIn.readUTF());
		
		//新增clientReceive物件用於接收Server廣播的聊天訊息
		clientReceive receive = new clientReceive(UserSocket);
		receive.start();
		
		//用於傳送訊息的無窮迴圈
		while(true){
			String Msgs = BufferReadIn.readLine();
			DataOut.writeUTF(Msgs);
			DataOut.flush();
			
			//若Msgs為EXIT，則終止receive執行緒並跳出迴圈
			if(Msgs.equalsIgnoreCase("EXIT")){
				receive.stop();
				break;
			}
		}
		System.out.println("Disconnect");
		keyin.close();
	}
}