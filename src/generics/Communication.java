package generics;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	
		Message message = new Message(null, null, null, null, null, null, null, null,null);
		Socket connectionToServer = null;
		
		try {
			connectionToServer = new Socket(host,port);
		} catch (IOException e) {
			System.out.println("Connection to server could not be established");
		}
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		try {
			oos = new ObjectOutputStream(connectionToServer.getOutputStream());
			oos.writeObject(stubObject);
			oos.flush();
		    
			
		} catch (IOException   e) {
			System.out.println("Object could not be sent to server");
			e.printStackTrace();
		}
		
		try {
			ois = new ObjectInputStream(connectionToServer.getInputStream());
			
			Object returnObject =ois.readObject();
			
			Class<?> thisClass = null;
			
			thisClass = Class.forName(returnObject.getClass().getCanonicalName());
			Class<?>[] noparams = {};
			Method method = null;
			Message returnMessage = null;
			
			method = thisClass.getDeclaredMethod("getMessage", noparams);
			 
			returnMessage = (Message)method.invoke(returnObject, (Object[])null);
			
			message = returnMessage;
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			
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
