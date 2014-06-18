package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectClient {
	

	Integer port = 9999;
	public void createConnection() throws IOException{
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
		while ( true ) {
			
		    try {
			Socket clientSocket = ss.accept();
		    ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		    try {
				Object o= ois.readObject();
				System.out.println(o.toString());
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		    
		    }   
		    
		    catch (IOException e) {
			System.out.println(e);
		    }
	
		}
	}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		finally{
			ss.close();
		}
	}

}
