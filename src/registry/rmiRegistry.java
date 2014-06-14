package registry;
import generics.*;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class rmiRegistry implements RegistryInterface {
	
	
	private String host;
	private int port;
	//Constructor to instantiate rmiRegistry 
	public rmiRegistry(String ipAddress, int port){
		this.setHost(ipAddress);
		this.setPort(port);
	}
	
	void bind(String name, RemoteObjectRef obj) throws  AlreadyBoundException, RemoteException{
		
	}
    void unbind(String name) throws RemoteException, NotBoundException{
    	
    }
    void rebind(String name, RemoteObjectRef obj) throws RemoteException{
    	
    }

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}