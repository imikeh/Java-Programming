package myjava.homework;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {

	private String address = "127.0.0.1";// 連線的ip
	private int port = 8888;
	String name;
	String ip = "";
	PrintStream writer;
	Socket sock;
	BufferedReader bReader;

	public client() {
		Socket clientSock = new Socket();
		InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
		try {
			clientSock.connect(isa, 10000);
			InputStreamReader isReader = new InputStreamReader(
					clientSock.getInputStream());
			writer = new PrintStream(clientSock.getOutputStream());

			bReader = new BufferedReader(isReader);
			Scanner scan = new Scanner(System.in);

			System.out.println(bReader.readLine());// 詢問使用者名稱
			String text = scan.nextLine();
			writer.println(text);
			System.out.println("Group List:" + bReader.readLine());// 讀取群組列表
			System.out.println(bReader.readLine());// 讀取使用說明
			//
			System.out.flush();

			text = scan.nextLine();
			writer.println(text);
			writer.flush();

			   ///建造新群組
			if (text.equals("1")) {
				System.out.println(bReader.readLine());
				text = scan.nextLine();
				writer.println(text);
				writer.flush();
				System.out.println(bReader.readLine());
				System.out.flush();
				///加入群組
			} else if (text.equals("2")) {
				System.out.println(bReader.readLine());
				text = scan.nextLine();
				writer.println(text);
				writer.flush();
				System.out.println(bReader.readLine());
			}

			Thread input = new Thread(new chat_in(clientSock));
			Thread output = new Thread(new chat_out(clientSock, scan));

			input.start();
			output.start();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public class chat_in implements Runnable {
		BufferedReader bReader;

		Socket mySock;

		public chat_in(Socket sock) {
			mySock = sock;
			InputStreamReader isReader;
			try {
				isReader = new InputStreamReader(mySock.getInputStream());

				bReader = new BufferedReader(isReader);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {

			while (true) {
				try {
					System.out.println(bReader.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public class chat_out implements Runnable {
		BufferedWriter bWriter;
		Socket mySock;
		Scanner myScan;

		public chat_out(Socket sock, Scanner scan) {
			mySock = sock;
			myScan = scan;
			try {
				bWriter = new BufferedWriter(new OutputStreamWriter(
						mySock.getOutputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void run() {

			while (myScan.hasNext()) {
				try {
					String msg = myScan.nextLine();
					if (msg.equals("EXIT")) {
						bWriter.write(msg + "\n");
						bWriter.flush();
						System.exit(0);
					}

					bWriter.write(msg + "\n");
					bWriter.flush();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new client();
	}

}
