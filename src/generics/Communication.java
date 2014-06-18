package generics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import registry.RegistryInterface;
import registry.Registry_stub;

public class Communication {

	String host;
	Integer port;
	Object stubObject;
	
	public Communication(String host, Integer port, Object stubObject){
		
		this.host = host;
		this.port = port;
		this.stubObject = stubObject;	
	}
	
	public Message connect(){
		
		Message message = new Message(null, null, null, null, null, null, null, null);
		Socket connectionToServer = null;
		//Add locate registry
		try {
			connectionToServer = new Socket(host,port);
		} catch (IOException e) {
			System.out.println("Connection to server could not be established");
		}
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(connectionToServer.getOutputStream());
		   
		} catch (IOException e) {
			System.out.println("Object Stream error");
			e.printStackTrace();
		}
		
		try {
			
			oos.writeObject(stubObject);
			oos.flush();
		    
			
		} catch (IOException   e) {
			System.out.println("Object could not be sent to server");
			e.printStackTrace();
		}
		
		try {
			ois = new ObjectInputStream(connectionToServer.getInputStream());
			RegistryInterface returnSkel = (Registry_stub)ois.readObject();
			
			
			message = returnSkel.getMessage();
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		try {
			oos.close();
			ois.close();
			connectionToServer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
		
	}
	
	
}
