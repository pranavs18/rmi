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
		    Object o;
		    try {
		    
		    /* At this point in registry we type casted this object to registry_stub 
		       and stored it as a Registry interface 
		       
		       eg. RegistryInterface r = (Registry_stub)ois.readObject 
		       
		       This is because - in case of registry we knew the stub and interfaces will always be Registry_stub
		       and RegistryInterface.
		       
		       In the client server interaction though the interface and stub are written by users. 
		       serverArithmeticInterface and serverArithmetic_stub (There may be many more like these)
		       
		       We need some way to do the following by looking at the object we obtain
		       
		       UsersProgramInterface interfaceReference = (UsersProgram_stub)ois.readObject();
		       
		       We do get get information about the class it is a type of and interfaces implemented by the object
		       by using getclass() and getClass().getInterfaces()
		       
		       but we need to use this information to perform 
		       
		       UsersProgramInterface  interfaceReference = (UsersProgram_stub)ois.readObject();
		        
		       at run time.This is where I am stuck!!!.
		       
		       We need to find a way to do this!!!
		       
		       After this we can use the stub class to get the message object and send the reply value through the stream and close
		       it and we are done
		       
		       */	
		    o = ois.readObject();
			
		    
		    
		    
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
