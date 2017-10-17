package myjava.homework;

import java.io.*;
import java.net.*;
import java.util.*;

class ClientReceiver extends Thread{

		private Socket clientSocket;
	
		public ClientReceiver(Socket clientSocket){
			this.clientSocket = clientSocket;
		}
	
		@Override public void run(){ /*override thread method run*/
			try{			
				InputStream inputStream = clientSocket.getInputStream();

				int lengthfromserver;
				byte[] bytefromserver = new byte[1000];

				while((lengthfromserver = inputStream.read(bytefromserver)) != -1){ /*�@��Ū���T���qserver*/
					System.out.println(new String(bytefromserver, 0, lengthfromserver));
				}
				this.clientSocket.close();
			}
			catch(UnknownHostException e){
				System.out.println("Host Error , Exit!");
				System.exit(-1);
			}
			catch(IllegalArgumentException e){
				System.out.println("Port Number Error , Exit!");
				System.exit(-1);
			}
			catch(IOException e){
				System.out.println("Error , Exit!");
				System.exit(-1);
			}
		}
}

public class client{
		public static void main(String[] args){

			Socket clientSocket;
			try{
	  			String ip;
	  			int port;
	  			Scanner scn = new Scanner(System.in);
	  			System.out.print("HOST:"); /*Ū��ip*/
	  			ip = scn.next();
	  			System.out.print("Port:"); /*Ū��port*/
	  			port = scn.nextInt();
		
				clientSocket = new Socket(ip, port);
				
				if(clientSocket.isConnected()){
		  			System.out.println("Connected");
		  			System.out.printf("\n___START___\n\n");
				}
					
				InputStream input = clientSocket.getInputStream();
				OutputStream output = clientSocket.getOutputStream();
				
				int lengthfromclient;
				byte[] bytefromclient = new byte[1000];
					
				System.out.println("Input user name:");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
				String username = br.readLine();
				output.write(username.getBytes());

				lengthfromclient = input.read(bytefromclient); /*��ܦ�����group�s�b*/
				System.out.println(new String(bytefromclient, 0, lengthfromclient));
					
				System.out.println("1.Add a new group.	  2.Join a specific group.");
				String group = br.readLine();
				output.write(group.getBytes());
				
				System.out.println("Input group name:");
				String groupName = br.readLine();
				output.write(groupName.getBytes());

				lengthfromclient = input.read(bytefromclient); /*��ܬO�гy�٬O�[�Jgroup*/
				System.out.println(new String(bytefromclient, 0, lengthfromclient));

				lengthfromclient = input.read(bytefromclient); /*��ܦ����ǤH�b�ogroup*/
				System.out.println(new String(bytefromclient, 0, lengthfromclient));	

				ClientReceiver clientthread = new ClientReceiver(clientSocket); /*�}�l�����T��*/
				clientthread.start();
				
				String inputmessage;					
				while(true)	{
					inputmessage = br.readLine();
					output.write(inputmessage.getBytes());
					if(inputmessage.equals("EXIT")){
						System.out.println("Disconnection");
						break;
					}
				}		
			}
			catch(UnknownHostException e){
				System.out.println("Host Error , Exit!");
				System.exit(-1);
			}
			catch(IllegalArgumentException e){
				System.out.println("Port Number Error , Exit!");
				System.exit(-1);
			}
			catch(IOException e){
				System.out.println("Error , Exit!");
				System.exit(-1);
			}
		}
}
