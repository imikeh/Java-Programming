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
		//�ǤJIP, portNum�إ�Socket
		Socket clientSocket = new Socket(IP, portNum);
		//�ǤJclientSocket����chatUser��k
		chatUser(clientSocket);

		keyin.close();
	}
	//�Ω󱵦�Server�ݸ�ƪ�Thread
	static class clientReceive extends Thread{
		private Socket UserSocket;
		public clientReceive(Socket socket){
			UserSocket = socket;
		}
		//��gThread�������k
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
		
		//�إ߸�Ʀ�y
		BufferedInputStream BIS = new BufferedInputStream(UserSocket.getInputStream());
		DataInputStream DataIn = new DataInputStream(BIS);
		BufferedOutputStream BOS = new BufferedOutputStream(UserSocket.getOutputStream());
		DataOutputStream DataOut = new DataOutputStream(BOS); 
		
		//�H�U�ǻ���Ƶ�Server
		System.out.println("Input user name: ");
		DataOut.writeUTF(BufferReadIn.readLine());
		DataOut.flush();
		
		//����Server�ǤJ����ơA�L�X��e�{��group
		System.out.print(DataIn.readUTF());
		
		System.out.println("1.Add a new group.   2.Join a specific group.");
		DataOut.writeInt(keyin.nextInt());
		DataOut.flush();
		System.out.println("Input group name: ");
		DataOut.writeUTF(BufferReadIn.readLine());
		DataOut.flush();
		
		//����Server�ǤJ��ơA�L�X�ҿ蠟Group������
		System.out.print(DataIn.readUTF());
		
		//�s�WclientReceive����Ω󱵦�Server�s������ѰT��
		clientReceive receive = new clientReceive(UserSocket);
		receive.start();
		
		//�Ω�ǰe�T�����L�a�j��
		while(true){
			String Msgs = BufferReadIn.readLine();
			DataOut.writeUTF(Msgs);
			DataOut.flush();
			
			//�YMsgs��EXIT�A�h�פ�receive������ø��X�j��
			if(Msgs.equalsIgnoreCase("EXIT")){
				receive.stop();
				break;
			}
		}
		System.out.println("Disconnect");
		keyin.close();
	}
}