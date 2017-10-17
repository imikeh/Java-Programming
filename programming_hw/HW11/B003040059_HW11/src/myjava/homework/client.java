/* Author: B003040059
 * Date: 2014 5 27
 * Purpose: Chat room for multiple people. The client receiver, sender and main class.
 */
package myjava.homework;

import java.io.*;
import java.net.*;

/* client receiver class */
class ClientReceiver extends Thread
{
	/* constant flag */
	private static final int BYTE_LEN = 1024;
	
	/* socket */
	private Socket clientSocket;
	
	/* constructor */
	public ClientReceiver(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
	/* override thread method run, insert the code that I wish to run it with thread */
	@Override public void run()
	{		
		try
		{			
			/* input stream that receive the text from Server */
			InputStream inputStream = clientSocket.getInputStream();
			
			/* some variables used get input */
			byte[] outputBytefromServer = new byte[BYTE_LEN];
			int ouputFromServerLength;
			
			/* if input stream is not reach to eof */
			/* then receive data from input stream */
			while((ouputFromServerLength = inputStream.read(outputBytefromServer)) != -1)
				System.out.println(new String(outputBytefromServer, 0, ouputFromServerLength));
			
			/* end socket connection */
			this.clientSocket.close();
		}
		catch(UnknownHostException e)
		{
			System.out.println("Unknown host! End Program!");
			System.exit(1);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Port Number Out of Bound! End Program!");
			System.exit(1);
		}
		catch(IOException e)
		{
			System.out.println("In ClientReceiver! Input or Output Error! End Program!");
			System.exit(1);
		}
	}
}

/* client sender class */
class ClientSender
{	
	/* constant flag */
	private static final int BYTE_LEN = 1024;
	private static final String LEAVE_CHAT = "EXIT";
	
	/* socket */
	private Socket clientSocket;
	
	/* start connect to server */
	public void go()
	{
		try
		{
			/* input host name and port number */
			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Host:");
			String hostName = keyin.readLine();
			System.out.print("Port:");
			int portNumber = Integer.parseInt(keyin.readLine());
			
			/* declare client socket*/
			clientSocket = new Socket(hostName, portNumber);
			if(clientSocket.isConnected())
			{
				System.out.println("Connected");
				System.out.println();
				System.out.println("___START___");
				System.out.println();
			}
			
			/* streams that communicate to Server */
			InputStream intputFromServer = clientSocket.getInputStream();
			OutputStream outputToServer = clientSocket.getOutputStream();
			
			/* some variables used get input */
			byte[] outputByteFromClient = new byte[BYTE_LEN];
			int outputFromClientLength;
			
			/* successfully log in then input user name, group choose and group name */
			System.out.println("Input user name:");
			String userName = keyin.readLine();
			outputToServer.write(userName.getBytes());
			
			/* ---------------------------------- */
			/* 		Server Group Information	  */
			outputFromClientLength = intputFromServer.read(outputByteFromClient);
			System.out.println(new String(outputByteFromClient, 0, outputFromClientLength));
			/* ---------------------------------- */
			
			System.out.println("1.Add a new group.\t2.join a specific group.");
			String group = keyin.readLine();
			outputToServer.write(group.getBytes());
			System.out.println("Input group name:");
			String groupName = keyin.readLine();
			outputToServer.write(groupName.getBytes());
			/* show how the situation that user create or join */
			outputFromClientLength = intputFromServer.read(outputByteFromClient);
			System.out.println(new String(outputByteFromClient, 0, outputFromClientLength));
			
			/* ---------------------------------- */
			/* 	show who is online in chat room   */
			outputFromClientLength = intputFromServer.read(outputByteFromClient);
			System.out.println(new String(outputByteFromClient, 0, outputFromClientLength));
			/* ---------------------------------- */
			
			/* start client receiver thread */
			ClientReceiver ClientRCV = new ClientReceiver(clientSocket);
			ClientRCV.start();
			
			/* keyboard input string */
			String clientKeyboardInput;	
			
			/* start send data to output stream */
			while(true)
			{
				clientKeyboardInput = keyin.readLine();
				outputToServer.write(clientKeyboardInput.getBytes());
				if(clientKeyboardInput.equals(LEAVE_CHAT))
				{
					System.out.println("Disconnection");
					break;
				}
			}		
		}
		catch(UnknownHostException e)
		{
			System.out.println("Unknown host! End Program!");
			System.exit(1);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Port Number Out of Bound! End Program!");
			System.exit(1);
		}
		catch(IOException e)
		{
			System.out.println("In ClientSender! Input or Output Error! End Program!");
			System.exit(1);
		}
	}
}

/* client Main class */
public class client
{
	public static void main(String[] args)
	{
		/* clientSender executes */
		ClientSender clientSDN = new ClientSender();
		clientSDN.go();
	}
}
