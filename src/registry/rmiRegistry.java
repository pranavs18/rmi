package registry;
import generics.myRemoteInterface;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class rmiRegistry implements RegistryInterface {
	
	
	private String host;
	private int port;
	ConcurrentHashMap<String, RemoteObjectRef> regMap = new ConcurrentHashMap<String,RemoteObjectRef>();
	
	//Constructor to instantiate rmiRegistry 
	public rmiRegistry(String ipAddress, int port){
		this.setHost(ipAddress);
		this.setPort(port);
	}
	
	public void bind(String name, RemoteObjectRef obj) throws  AlreadyBoundException, RemoteException{
		if(!regMap.containsKey(name)){
			regMap.put(name, obj);
			
		}
		else{
			throw new AlreadyBoundException("This name is already bound");
		}
	}
	
    public void unbind(String name) throws RemoteException, NotBoundException{
    	
    }
    
    public void rebind(String name, RemoteObjectRef obj) throws RemoteException{
    	
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