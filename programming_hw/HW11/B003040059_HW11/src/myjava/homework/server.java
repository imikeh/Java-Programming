/* Author: B003040059
 * Date: 2014 5 27
 * Purpose: Chat room for multiple people. The server receiver, acceptor, map and main class.
 */
package myjava.homework;

import java.io.*;
import java.net.*;
import java.util.*;

/* server acceptor accepts any clients to log in */
class Acceptor
{	
	/* accept clients */
	public void accept()
	{
		try
		{			
			/* input port number */
			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Port:");
			int portNumber = Integer.parseInt(keyin.readLine());
			
			/* construct server socket */
			ServerSocket serverSocket = new ServerSocket(portNumber);
			
			/* block until any client has connected */
			while(true)
			{				
				/* accept client and start a new receive thread */
				Socket connection = serverSocket.accept();
				ServerReceiver SR = new ServerReceiver(connection);
				SR.start();
				System.out.println("new one come in");
				System.out.println();
				
				/* output how many people in group (asynchronous refresh)*/
				Iterator<String> itGroup = SystemMap.getGroupMap().keySet().iterator();
				String tmpGroup;
				while(itGroup != null && itGroup.hasNext())
				{
					tmpGroup = itGroup.next();
					System.out.println("Group in Server:");
					System.out.println("There are " + SystemMap.getGroupMap().get(tmpGroup).size() + " people in " + tmpGroup);
				}
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Port Number Out of Bound! End Program!");
			System.exit(1);
		}
		catch(IOException e)
		{
			System.out.println("Input or Output Error! End Program!");
			System.exit(1);
		}
	}
}

/* server receiver class */
class ServerReceiver extends Thread
{	
	/* constant flag */
	private static final int BYTE_LEN = 1024;
	private static final String LEAVE_CHAT = "EXIT";
	
	/* socket data */
	private Socket connectionSocket;
	
	/* constructor */
	public ServerReceiver(Socket connectionSocket)
	{
		this.connectionSocket = connectionSocket;
	}
	
	/* override thread method run, insert the code that I wish to run it with thread */
	@Override public void run()
	{
		try
		{
			/* streams that communicate to Client */
			InputStream inputStream = connectionSocket.getInputStream();
			OutputStream outputStream = connectionSocket.getOutputStream();
			
			/* some variables used get input */
			byte[] outputByteFromClient = new byte[BYTE_LEN];
			int outputFromClientLength;
			
			/* read user information of name and group */
			outputFromClientLength = inputStream.read(outputByteFromClient);		
			String userName = new String(outputByteFromClient, 0, outputFromClientLength);
			System.out.println("user name:" + userName);
			
			/* ask user to join a specific group or create new one */
			if(!SystemMap.getGroupMap().isEmpty())
			{
				/* send how many messages will send length: groups + 2 */
				String whatGroupInServer = "Groups in Server:\n";
				Iterator<String> itGroup = SystemMap.getGroupMap().keySet().iterator();
				while(itGroup.hasNext())
					whatGroupInServer += itGroup.next() + "\n";
				outputStream.write(whatGroupInServer.getBytes());
			}
			else
				/* send how many messages will send length: 1 */
				outputStream.write(("No groups in Server.").getBytes());
			
			outputFromClientLength = inputStream.read(outputByteFromClient);		
			int group = Integer.parseInt(new String(outputByteFromClient, 0, outputFromClientLength));
			outputFromClientLength = inputStream.read(outputByteFromClient);		
			String groupName = new String(outputByteFromClient, 0, outputFromClientLength);
			if(group == 1)
			{
				SystemMap.CreateNewGroup(groupName);
				SystemMap.addMemberToGroup(groupName, userName);
				System.out.println("New group:" + groupName + " created");
				outputStream.write(("Create group " + groupName + " successed!").getBytes());
			}
			else if(group == 2)
			{
				SystemMap.addMemberToGroup(groupName, userName);
				outputStream.write(("Join group \"" + groupName + "\" successed!").getBytes());
			}
			
			/* link the user name of client and its output stream together */
			SystemMap.setOutputStreamMap(userName, this.connectionSocket.getOutputStream());
			
			/* send who is in chat room to client */
			Iterator<String> whoIsInChatRoom = SystemMap.getGroupMap().get(groupName).iterator();
			String onlineMsg = groupName + " now has:\n";
			while(whoIsInChatRoom.hasNext())
				onlineMsg += whoIsInChatRoom.next() + "\n";
			outputStream.write(onlineMsg.getBytes());			
			
			/* start receive data from specific user */
			String receiveData;
			while(true)
			{
				outputFromClientLength = inputStream.read(outputByteFromClient);
				if((receiveData = new String(outputByteFromClient, 0, outputFromClientLength)).equals(LEAVE_CHAT))
				{
					/* close output stream and send eof to client receiver's input stream */
					connectionSocket.shutdownOutput();
					
					/* remove associated information of this user */
					SystemMap.removeClient(userName);
					SystemMap.removeMemberFromGroup(groupName, userName);
					SystemMap.broadcastMsg(groupName, userName + " has leave chat room.");
					SystemMap.check();
					break;
				}
				/* according to the group which the user belongs, broadcast to that group */
				else
					SystemMap.broadcastMsg(groupName, userName + " says:" + receiveData);				
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Port Number Out of Bound! End Program!");
			System.exit(1);
		}
		catch(IOException e)
		{
			System.out.println("In ServerReceiver! Input or Output Error! End Program!");
			System.exit(1);
		}
	}
}

class SystemMap
{
	/* map of user's name and its output stream */
	private static Map<String, OutputStream> outputStreamMap = new HashMap<String, OutputStream>();
	
	/* add new user and its output stream */
	public static void setOutputStreamMap(String userName, OutputStream outputStream)
	{
		outputStreamMap.put(userName, outputStream);
	}
	
	/* decide to broadcast to specific group */
	public static void broadcastMsg(String groupName, String msg)
	{
		try
		{
			String tmpGroup;
			Iterator<String> itGroup = groupAndUsers.keySet().iterator();
			/* find which group to broadcast */
			while(itGroup.hasNext())
			{
				if((tmpGroup = itGroup.next()).equals(groupName))
				{
					/* has found the specific group then write to their output stream */
					Iterator<String> itUser = groupAndUsers.get(tmpGroup).iterator();
					while(itUser.hasNext())
						outputStreamMap.get(itUser.next()).write(msg.getBytes());
				}
			}
		}
		catch(IOException e)
		{
			System.out.println("In Method broadcastMsg, Input or Output Error! End Program!");
			System.exit(1);
		}
	}
	
	/* remove user and its output stream */
	public static void removeClient(String userName)
	{
		outputStreamMap.remove(userName);
	}
	
	/* map of the group and its members */
	private static Map<String, ArrayList<String>> groupAndUsers = new HashMap<String, ArrayList<String>>();
	
	/* create a new group */
	public static void CreateNewGroup(String groupName)
	{
		groupAndUsers.put(groupName, new ArrayList<String>());
	}
	
	/* add user to specific group */
	public static void addMemberToGroup(String groupName, String userName)
	{
		groupAndUsers.get(groupName).add(userName);		
	}
	
	/* remove user from specific group */
	public static void removeMemberFromGroup(String groupName, String userName)
	{
		groupAndUsers.get(groupName).remove(userName);
	}
	
	/* accessor */
	public static Map<String, ArrayList<String>> getGroupMap()
	{
		return groupAndUsers;
	}
	
	public static void check()
	{
		Map<String, ArrayList<String>> newGroupMap = new HashMap<String, ArrayList<String>>();
		Iterator<String> itGroup = groupAndUsers.keySet().iterator();
		String tmpGroup;
		while(itGroup.hasNext())
			if(!groupAndUsers.get(tmpGroup = itGroup.next()).isEmpty())
				newGroupMap.put(tmpGroup, groupAndUsers.get(tmpGroup));
		
		groupAndUsers = newGroupMap;
	}
}

/* server Main class */
public class server
{
	public static void main(String[] args)
	{	
		/* accept clients */
		Acceptor acp = new Acceptor();
		acp.accept();
	}
}

