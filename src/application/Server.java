package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.layout.VBox;

public class Server {
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	public Server(ServerSocket serverSocket)  {
		this.serverSocket = serverSocket;
		try {
			this.socket = serverSocket.accept();
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			System.out.println("error Creating Server");
			e.printStackTrace();
		}
		
	}
public void sendMessageToParent(String messageToParent) {
	try {
		bufferedWriter.write(messageToParent);
		bufferedWriter.newLine();
		bufferedWriter.flush();
		
	} catch(IOException e) {
		e.printStackTrace();
		System.out.println("error sending message to the parent");
		closeEverything(socket , bufferedReader,bufferedWriter);
		
	}
}
public int recieveId(VBox vBox) {
	try {
		String messageFromParent= bufferedReader.readLine();
		int id = Integer.parseInt(messageFromParent);
		return id;
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 0;
	}
}
public void recieveMessageFromClient(VBox vBox) {
	new Thread(new Runnable() {
		@Override
		public void run() {
			while(socket.isConnected()) {
				try {
				String messageFromParent= bufferedReader.readLine();
				ServerController.addLabel(messageFromParent,vBox );
				System.out.println("test");
				
				}
				catch(IOException e) {
					e.printStackTrace();
					System.out.println("Error recieving message From the parent");
					closeEverything(socket,bufferedReader,bufferedWriter);
					break;
				}
			}
			
		}
	}).start();
}
public void closeEverything(Socket socket , BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
	try {
		if(bufferedReader != null) {
			bufferedReader.close();
		}
		if(bufferedWriter != null) {
			bufferedWriter.close();
		}
		if(socket!=null) {
			socket.close();
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
}


}