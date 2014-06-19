package Server;

import generics.Message;
import generics.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
		    Message msg;
		    try {
		    
		    
		    	msg = (Message)ois.readObject();
		    	String key = msg.getLookupName();
		    	Object o = ObjectMap.getObjectFromKey(key);
		    	
		    	Class<?> thisClass = null;
				System.out.println(msg.getRor().getClass_Name()); //remove
				System.out.println("Ahahahahahah "+ o.getClass().getCanonicalName()); //remove
				
				thisClass = Class.forName(msg.getRor().getClass_Name());
				
				Method method = null;
				Message returnMessage = null;
				Object returnValue = null;
				Object newObj[] = new Object[msg.getArguments().length];
				newObj = msg.getArguments();
				
				
				
				Class<?>[] argTypes = new Class[newObj.length];
				for (int i = 0; i < newObj.length; i++) {
					
					argTypes[i] = newObj[i].getClass();
					
					if(!argTypes[i].isPrimitive()){
						argTypes[i]= int.class;
					}
					
				} 
				
				
					method = thisClass.getMethod(msg.getMethodName(), argTypes);
				 try {
					returnValue = method.invoke(o, newObj);
					
					returnMessage = new Message(MessageType.RETURN, msg.getMethodName(), msg.getArguments(), msg.getArgTypes(), msg.getReturnType(), returnValue, null, null,null);
					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
					oos.writeObject(returnMessage);
					oos.close();
				 
				 } catch (InvocationTargetException e) {
					
					Throwable cause = e.getCause();
					returnMessage = new Message(MessageType.EXCEPTION, msg.getMethodName(), msg.getArguments(), msg.getArgTypes(), msg.getReturnType(), null, cause.getMessage(), msg.getRor(),null);
					ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
					oos.writeObject(returnMessage);
					oos.close();
				}
		    
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException  | IllegalAccessException | IllegalArgumentException e
					) {

				e.printStackTrace();
			}
		
		    ois.close();
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
