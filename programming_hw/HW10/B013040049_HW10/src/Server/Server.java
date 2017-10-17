package Server;

import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Server extends Thread{
		
		public Server(Socket s){  
			this.server = s;  
		}  
		
		public class serverthread extends Thread{  
	    
			Socket serverSocket;  
			
			public void run(){  
				try{
					BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
					String input = new String();  
	              
					while(true){  
						input = in.readLine();  
						System.out.println("[client] : " + input);  
						if(input.equals("_QUIT")){
							throw new IOException();
						}  
					} 
				}
				catch (IOException e){
					System.out.println("Disconnection");
					System.exit(-1);
				}  
			}
	    
			public serverthread(Socket server){  
				serverSocket=server;  
			}  
		} 
	
	    Socket server;    
  
    	public void run(){  
    		try{ 
    			serverthread thread = new serverthread(server);  
    			thread.start();  
  
    			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
    			PrintWriter pw = new PrintWriter(server.getOutputStream());  
    			while (true){  
    				String s = in.readLine();  
    				pw.println(s);  
    				pw.flush();  
    			}  
    		}
    		catch (IOException e){
    			System.out.println("Disconnection");
    			System.exit(-1);  
    		}
    	}  
  
    	public static void main(String[] args) throws IOException{
    		int port;
    		Scanner scn = new Scanner(System.in);
    		System.out.print("Port:");
    		port = scn.nextInt();
    		System.out.println("Open Port " + port +" sucessfully");
    		ServerSocket server = new ServerSocket(port);  
    		Socket socket;
    		while(true){
    			socket = server.accept(); 
    			Server thread = new Server(socket);  
    			thread.start();
    			System.out.println("Accept client");
    			System.out.printf("\n___START___\n\n");
    		}
    	}
}

 