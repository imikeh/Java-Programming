package myjava.homework;
import java.io.*;
import java.net.*;
import java.util.*;

public class server {
	
	//�ΥH�����s�զW�٩M�s�դH��
	class groupRecord {
		private String groupName; //�s�զW��
		private int memberCunter = 1;  //

		public groupRecord(String name) {
			this.groupName = name;
		}

		public int getMCunter() {
			return this.memberCunter;
		}

		public String getName() {
			return this.groupName;
		}

		public void addMCunter() {
			this.memberCunter++;
		}

	}
	//�ΥH�����ϥΪ̦W�٥H�ΨϥΪ̪�output
	class memberData{
		String memberName;
		BufferedWriter memberOutput;
		
		public memberData(String mN,BufferedWriter mO)
		{
			this.memberName = mN;
			this.memberOutput = mO;
		}
	}

	List<List<memberData>> groupMember = new ArrayList<List<memberData>>(); //�إߤG��arraylist�ΥH�s��s�զ���
	Map<String, Integer> groupMap = new HashMap<String, Integer>(); //<groupName,ArrayListIndex> �s�չ������}�C
	private final int serverPort = 8888;
	private ServerSocket myServer;
	ArrayList<groupRecord> groupTable = new ArrayList<groupRecord>();  //�����s�դH��

	public void startServer() {

		try {
			myServer = new ServerSocket(serverPort);

			while (true) {
				Socket cSocket = myServer.accept();
				System.out.println("new one come in\n");

				Thread t = new Thread(new Process(cSocket));
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("connection failed");
			e.printStackTrace();
		}
	}

	public class Process implements Runnable {

		BufferedReader bReader;
		BufferedWriter bWriter;
		Socket sock;

		public Process(Socket cSocket) {
			sock = cSocket;
			try {
				InputStreamReader isReader = new InputStreamReader(
						sock.getInputStream());

				bReader = new BufferedReader(isReader);

				bWriter = new BufferedWriter(new OutputStreamWriter(
						sock.getOutputStream()));
	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void run() {
			int mIndex = 0;// �������ϥΪ��ݩ�list���ĴX�C������
			String message;//�s�񦬨�ϥΪ̰e�X���T��
			String uName;  //�ϥΪ̦W��
			String gList ="";
			String tempGroupName;//�s�զW��(�����U�[�J���s�թηs�W���s�զW��)
			while (true) {
				try {

					bWriter.write("Input user name:\n");
					bWriter.flush();
					uName = bReader.readLine().toString();
					System.out.println("user name:"+uName);
					
					///�d��ثe�Ҧ����s�ըöǵ��ϥΪ�
					if(groupMap.size()>0){
					
					gList = gList + groupMap.keySet()+"\n";
					bWriter.write(gList);
					bWriter.flush();
					}
					else
					{
						bWriter.write("No group now!!\n");
						bWriter.flush();
					}
					///
					
					bWriter.write("1.Add a new group.\t2.Join a specific group.\n");
					bWriter.flush(); 
					String cho = bReader.readLine();
					
					//�s�W�s��
					if (cho.equals("1") ) {
						
						bWriter.write("Input group name:\n");
						bWriter.flush();
						tempGroupName = bReader.readLine();  
						
						boolean det = false;
						
						
						// �ˬd���s�լO�_�w�s�b
						for (int i = 0; i < groupTable.size(); i++) {
							if (groupTable.get(i).getName() == tempGroupName) {
								det = true;
							}
						}
						//�p�s�դ��s�b�A�h�s�W���s�աA�ð���������
						if (!det) {
							memberData md = new memberData(uName, bWriter);
							groupTable.add(new groupRecord(tempGroupName)); //�N���s�զW�[�J�C��
							List<memberData> groupList = new ArrayList<memberData>(); //�s��P�@�s�զ������}�C
							groupList.add(md);
							groupMember.add(groupList);
							mIndex = groupMember.size() -1;//���Ӹs�թҹ���2��arraylist��index
							groupMap.put(tempGroupName, groupMember.size() - 1);//�Q��map�����s�զW�٩M�������index
							bWriter.write("join " + tempGroupName + " success!\n");
							bWriter.flush();
							System.out.println("New group:"+tempGroupName+" created");
							
							
							

							break;
						} else {
							continue;
						}
					} 
					//�[�J�s��
					else if (cho.equals("2")) {
						
						bWriter.write("Input group name:\n");
						bWriter.flush();
						tempGroupName = bReader.readLine();
						memberData md = new memberData(uName, bWriter);
						
						//���������s��
						for (int i = 0; i < groupTable.size(); i++) {
							if (tempGroupName.equals( groupTable.get(i).getName())) {
								
								groupTable.get(i).addMCunter();
								mIndex = groupMap.get(groupTable.get(i) //�s��[�J�s�ժ�index
										.getName());
								groupMember.get(mIndex).add(md);//�[�J�s��
								bWriter.write("join group \""+tempGroupName+"\" successed\n");
								bWriter.flush();
								break;
							} 
/*							else if (i == (groupTable.size() - 1)) {
								continue;
							}*/
						}
						///
						String mList ="";
						//�d��P�s�թҦ�������
						for(int i=0;i< groupMember.get(mIndex).size();i++)
						{
							mList = mList + groupMember.get(mIndex).get(i).memberName +"\n"; 
						}
						
						bWriter.write("Group mamber:\n"+"[\n"+mList+"]\n");
						bWriter.flush();
						break;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//�B�zmessage
			try {
				while ((message = bReader.readLine()) != null) {
					
					if(message.equals("EXIT"))
					{
						for(int i=0;i<groupTable.size();i++)
						{
							if(tempGroupName.equals(groupTable.get(i).groupName))
							{
								groupTable.get(i).memberCunter--;//�s�դH�ƴ�1
								//���s�դw�L�H�ɭ簣�Ӹs��
								if(groupTable.get(i).memberCunter == 0)
								{
									int rmInd = groupMap.get(tempGroupName);
									groupMember.remove(rmInd);
									groupMap.remove(tempGroupName);
									 for (String key : groupMap.keySet()) {
								         
										 if(groupMap.get(key) > rmInd)
										 {
											 groupMap.put(key, groupMap.get(key)-1);
										 }
										 
								        }
									
									groupTable.remove(i);
								break;
								}
								else
								{
									for(int j = 0;j<groupMember.get(groupMap.get(tempGroupName)).size();j++)
									{
										if(groupMember.get(groupMap.get(tempGroupName)).get(j).memberName.equals(uName))
										{
												groupMember.get(groupMap.get(tempGroupName)).remove(j);
												break;
										}
									}
								
									
								}
							}
							
						}
						break;
					}
					else{
					tellGroup(message, mIndex,uName);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		

	}
	
	//�ǰT�����P�@�s�զ���   (�T��,index,�o�ܪ�)
	public void tellGroup(String msg, int index, String name ) {

		String memberName = name;
		
		for (int i = 0; i < groupMember.get(index).size(); i++) {
			if(!name.equals(groupMember.get(index).get(i).memberName)){
			try {
				
				groupMember.get(index).get(i).memberOutput.write(memberName+" say:"+msg+"\n");
				groupMember.get(index).get(i).memberOutput.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new server().startServer();
	}

}
