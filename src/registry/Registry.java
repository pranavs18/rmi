package registry;
import generics.myRemoteInterface;

import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Registry implements RegistryInterface, Runnable {
	
	
	private String host;
	private int port;
	ConcurrentHashMap<String, RemoteObjectRef> regMap = new ConcurrentHashMap<String,RemoteObjectRef>();
	
	//Constructor to instantiate rmiRegistry 
	public Registry(String ipAddress, int port){
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

	@Override
	public void run() {
		while (true) {
                
                 System.out.println("Accept incoming connections");
                 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
               

              
        
		
	           }
	}
	
}