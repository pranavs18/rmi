package registry;
import generics.*;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class rmiRegistry implements RegistryInterface {
	
	
	private String bindName;
	private int port;
	//Constructor to instantiate rmiRegistry 
	public rmiRegistry(String bindName, int port){
		this.bindName = bindName;
		this.port = port;
	}
	
	void bind(String name, Remote obj) throws  AlreadyBoundException, RemoteException{
		
	}
    void unbind(String name) throws RemoteException, NotBoundException{
    	
    }
    void rebind(String name, Remote obj) throws RemoteException{
    	
    }
	
}