package generics;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Communication {

	String host;
	Integer port;
	Object stubObject;
	
	public Communication(String host, Integer port, Object stubObject){
		
		this.host = host;
		this.port = port;
		this.stubObject = stubObject;	
	}
	
	public void connect(){
		
		Socket connectionToServer = null;
		
		try {
			connectionToServer = new Socket(host,port);
		} catch (IOException e) {
			System.out.println("Connection to server could not be established");
		}
		
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(connectionToServer.getOutputStream());
		} catch (IOException e) {
			System.out.println("Object Stream error");
			e.printStackTrace();
		}
		
		try {
			oos.writeObject(stubObject);
			oos.flush();
		} catch (IOException e) {
			System.out.println("Object could not be sent to server");
			e.printStackTrace();
		}
		try {
			connectionToServer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
