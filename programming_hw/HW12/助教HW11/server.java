package myjava.homework;
import java.io.*;
import java.net.*;
import java.util.*;

public class server {
	
	//用以紀錄群組名稱和群組人數
	class groupRecord {
		private String groupName; //群組名稱
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
	//用以紀錄使用者名稱以及使用者的output
	class memberData{
		String memberName;
		BufferedWriter memberOutput;
		
		public memberData(String mN,BufferedWriter mO)
		{
			this.memberName = mN;
			this.memberOutput = mO;
		}
	}

	List<List<memberData>> groupMember = new ArrayList<List<memberData>>(); //建立二維arraylist用以存放群組成員
	Map<String, Integer> groupMap = new HashMap<String, Integer>(); //<groupName,ArrayListIndex> 群組對應的陣列
	private final int serverPort = 8888;
	private ServerSocket myServer;
	ArrayList<groupRecord> groupTable = new ArrayList<groupRecord>();  //紀錄群組人數

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
			int mIndex = 0;// 紀錄此使用者屬於list中第幾列的成員
			String message;//存放收到使用者送出之訊息
			String uName;  //使用者名稱
			String gList ="";
			String tempGroupName;//群組名稱(紀錄愈加入之群組或新增之群組名稱)
			while (true) {
				try {

					bWriter.write("Input user name:\n");
					bWriter.flush();
					uName = bReader.readLine().toString();
					System.out.println("user name:"+uName);
					
					///查找目前所有的群組並傳給使用者
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
					
					//新增群組
					if (cho.equals("1") ) {
						
						bWriter.write("Input group name:\n");
						bWriter.flush();
						tempGroupName = bReader.readLine();  
						
						boolean det = false;
						
						
						// 檢查此群組是否已存在
						for (int i = 0; i < groupTable.size(); i++) {
							if (groupTable.get(i).getName() == tempGroupName) {
								det = true;
							}
						}
						//如群組不存在，則新增此群組，並做相關紀錄
						if (!det) {
							memberData md = new memberData(uName, bWriter);
							groupTable.add(new groupRecord(tempGroupName)); //將此群組名加入列表
							List<memberData> groupList = new ArrayList<memberData>(); //存放同一群組成員之陣列
							groupList.add(md);
							groupMember.add(groupList);
							mIndex = groupMember.size() -1;//找到該群組所對應2維arraylist的index
							groupMap.put(tempGroupName, groupMember.size() - 1);//利用map紀錄群組名稱和其對應的index
							bWriter.write("join " + tempGroupName + " success!\n");
							bWriter.flush();
							System.out.println("New group:"+tempGroupName+" created");
							
							
							

							break;
						} else {
							continue;
						}
					} 
					//加入群組
					else if (cho.equals("2")) {
						
						bWriter.write("Input group name:\n");
						bWriter.flush();
						tempGroupName = bReader.readLine();
						memberData md = new memberData(uName, bWriter);
						
						//找到對應的群組
						for (int i = 0; i < groupTable.size(); i++) {
							if (tempGroupName.equals( groupTable.get(i).getName())) {
								
								groupTable.get(i).addMCunter();
								mIndex = groupMap.get(groupTable.get(i) //存放加入群組的index
										.getName());
								groupMember.get(mIndex).add(md);//加入群組
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
						//查找同群組所有的成員
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
			
			//處理message
			try {
				while ((message = bReader.readLine()) != null) {
					
					if(message.equals("EXIT"))
					{
						for(int i=0;i<groupTable.size();i++)
						{
							if(tempGroupName.equals(groupTable.get(i).groupName))
							{
								groupTable.get(i).memberCunter--;//群組人數減1
								//此群組已無人時剔除該群組
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
	
	//傳訊息給同一群組成員   (訊息,index,發話者)
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
