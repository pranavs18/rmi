package Server;

import java.io.IOException;

import generics.Naming;
import registry.AlreadyBoundException;
import registry.RemoteException;
import registry.RemoteObjectRef;

public class remoteServer{
	
	public static void main(String args[]) throws IOException{
		
		regServerTest test = new regServerTest();
		
		try {
			Naming.bind("//127.0.0.1:1099/firstObject", test);
			Naming.rebind("//127.0.0.1:1099/firstObject", test);
		} catch (AlreadyBoundException | RemoteException e) {
			
			e.printStackTrace();
		}
		
	}

}