package Client;

import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Client extends Thread{  
    
    	static Socket socket;  
    
  		public void run(){  
  			try{  
  				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
  				String input = new String();  
  				while(true){  
  					input = in.readLine();  
  					System.out.println("[Server] : " + input);
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
     
    
  		public static void main(String[] args) throws IOException{
  			String ip;
  			int port;
  			Scanner scn = new Scanner(System.in);
  			System.out.print("HOST:");
  			ip = scn.next();
  			System.out.print("Port:");
  			port = scn.nextInt();
	  
  			socket=new Socket(ip,port);  
  
  			Client thread=new Client();  
  			thread.start();  
  			System.out.println("Connected");
  			System.out.printf("\n___START___\n\n");
  			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
  			PrintWriter pw = new PrintWriter(socket.getOutputStream());  
        
  			String s = new String();  
 
  			while(!s.equals("_QUIT")){
  				s = in.readLine();  
  				pw.println(s);  
  				pw.flush();  
  			}      
  			pw.close();  
  			socket.close();
  			scn.close();
  		}  
}