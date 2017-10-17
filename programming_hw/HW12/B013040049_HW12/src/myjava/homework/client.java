package myjava.homework;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class client{
	
	static private BufferedInputStream BIS;
	static private DataInputStream DataIn;
	static private BufferedOutputStream BOS;
	static private DataOutputStream DataOut;
	static private boolean isConnectEnter = false;
	static private boolean isSetEnter = false;
	static private boolean haveMessage = false;
	static private boolean end = false;
	static private String ip;
	static private int port;
	static private String username;
	static private int group;
	static private String groupName;		
	
		public static void main(String[] args) throws Exception{
			
			Connect connect = new Connect();
			connect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			connect.setBounds(500, 250, 220, 120); // set x, y, width, height	
			connect.setResizable(false); // set fixed window
			connect.setVisible(true);		
			
			while(!isConnectEnter);

			Socket clientSocket;
			try{
				clientSocket = new Socket(ip, port);
				
				BIS = new BufferedInputStream(clientSocket.getInputStream());
				DataIn = new DataInputStream(BIS);
				BOS = new BufferedOutputStream(clientSocket.getOutputStream());
				DataOut = new DataOutputStream(BOS); 	

				String groupMsgs = DataIn.readUTF();
				Set set = new Set(groupMsgs);
				set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				set.setBounds(500, 250, 270, 300);
				set.setResizable(false);
				set.setVisible(true);
				
				while(!isSetEnter);
				DataOut.writeUTF(username);
				DataOut.flush();
				DataOut.writeInt(group);
				DataOut.flush();
				DataOut.writeUTF(groupName);
				DataOut.flush();
				
				groupMsgs = DataIn.readUTF();
				final Chat chat = new Chat(groupMsgs);
				chat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				chat.setBounds(500, 250, 480, 350);
				chat.setResizable(false);
				chat.setVisible(true);				
				
				Thread receiver = new Thread(new Runnable() {
		        	public void run() {
		                try {
		                    while(true){
		                   		chat.setmsgsArea(DataIn.readUTF());
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
		        });
				receiver.start();
				
				String inputmessage;					
		
				while(!end){
					if(haveMessage){
						inputmessage = chat.getInput();
						DataOut.writeUTF(inputmessage);
						DataOut.flush();
						chat.setInputField();
						haveMessage = false;
						if(inputmessage.equalsIgnoreCase("EXIT"))
							System.exit(0);
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
		
		static class Chat extends JFrame implements ActionListener{
			private JTextArea msgsArea;
			private JScrollPane scroll;
			private JTextField inputField;
			private JButton EnterButton;
			public Chat(String msgs){
				super(groupName+" Chatroom");
				
				setLayout(new FlowLayout());
				
				msgsArea = new JTextArea(msgs+"\n" ,15, 40);
				msgsArea.setEditable(false);			
				scroll = new JScrollPane(msgsArea);
				add(scroll);
				
				inputField = new JTextField(30);
				add(inputField);
				
				EnterButton = new JButton("Enter");
				EnterButton.setMnemonic(KeyEvent.VK_S);
				EnterButton.addActionListener(this);
				add(EnterButton);
			}
			public String getInput(){
				return inputField.getText();
			}
			public void setInputField(){
				inputField.setText("");
			}
			public void setmsgsArea(String newMsgs){
				msgsArea.setText(msgsArea.getText() + newMsgs);
			}
			public void actionPerformed (ActionEvent evt){
				if(evt.getSource()==EnterButton){
					haveMessage = true;			
				}
			}
		}
		
		static class Set extends JFrame implements ActionListener{	
			private JLabel usernameLabel;
			private JLabel groupChoiceLabel;
			private JLabel groupNameLabel;
			private JTextField usernameField;
			private JTextField groupChoiceField;
			private JTextField groupNameField;	
			private JTextArea showGroupArea;	
			private JButton EnterButton;
			
			public Set(String msgs)throws Exception{
				super("Set Name and Group");
				
				setLayout(new FlowLayout());
				
				usernameLabel = new JLabel("Input user name:");
				add(usernameLabel);		
				usernameField = new JTextField(10);
				add(usernameField);		
				
				groupChoiceLabel = new JLabel("1.Add a new group.   2.Join a specific group.");
				add(groupChoiceLabel);		
				groupChoiceField = new JTextField(3);
				add(groupChoiceField);		
				
				showGroupArea = new JTextArea(msgs, 6, 20);
				showGroupArea.setEditable(false);
				add(showGroupArea);
				
				groupNameLabel = new JLabel("Input group name:");
				add(groupNameLabel);
				groupNameField = new JTextField(10);
				add(groupNameField);		
				EnterButton = new JButton("Enter");
				EnterButton.setMnemonic(KeyEvent.VK_S);
				EnterButton.addActionListener(this);
				add(EnterButton);
			}
			public void actionPerformed(ActionEvent evt){
				if(evt.getSource()==EnterButton){
					username = usernameField.getText();
					group = Integer.parseInt(groupChoiceField.getText());
					groupName = groupNameField.getText();
					isSetEnter = true;
				}
			}
		}		
		
		static class Connect extends JFrame implements ActionListener{
			private JLabel hostLabel;
			private JLabel portLabel;
			private JTextField hostField;
			private JTextField portField;
			private JButton connectButton;
			
			public Connect(){			
				super("Server connect"); /* set frame title */
				
				setLayout(new FlowLayout());/* set layout */
				
				hostLabel = new JLabel("HOST:");									
				hostField = new JTextField(15);
				portLabel = new JLabel("PORT:");
				portField = new JTextField(15);
				connectButton = new JButton("Connect");
				connectButton.setMnemonic(KeyEvent.VK_S);
				connectButton.addActionListener(this);
				add(hostLabel);
				add(hostField);
				add(portLabel);
				add(portField);
				add(connectButton);
		
			}	
			public void actionPerformed(ActionEvent evt){
				if(evt.getSource()==connectButton){
					ip = hostField.getText();
					port = Integer.parseInt(portField.getText());
					isConnectEnter = true;
				}
			}
		}
		
}


