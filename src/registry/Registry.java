package registry;
import generics.myRemoteInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Registry implements RegistryInterface, Runnable {
	
	public final static int registry_port = 1099;
	private static String host;
	private int port;
	ConcurrentHashMap<String, RemoteObjectRef> regMap;

	//Constructor to instantiate rmiRegistry 
	public Registry(String ipAddress, int port){
		this.setHost(ipAddress);
		this.setPort(port);
		regMap = new ConcurrentHashMap<String, RemoteObjectRef>();
	}
	
	public void bind(String name, RemoteObjectRef obj) throws  AlreadyBoundException, RemoteException{
		if(!regMap.containsKey(name)){
			RemoteObjectRef ROR = new RemoteObjectRef(host,port,name,obj.class_name);
			regMap.put(name, ROR);			
		}
		else{
			throw new AlreadyBoundException("This name - " + name+ " is already bound");
		}
	}
	
    public void unbind(String name) throws RemoteException, NotBoundException{
    	if(regMap.containsKey(name)){
			
			regMap.remove(name);			
		}
    	else{
    		throw new NotBoundException("This name is not binded");
    	}
    }
    
    public void rebind(String name, RemoteObjectRef obj) throws RemoteException{
    	
    	RemoteObjectRef ROR = new RemoteObjectRef(host,port,name,obj.class_name);
		regMap.put(name, ROR);	
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
                
			try {
				try {
					startRegistry(host, registry_port);
					
				} catch (ClassNotFoundException | InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
   
	}
	
private void startRegistry(String host, int registryPort) throws IOException, ClassNotFoundException, InterruptedException {
		
	@SuppressWarnings("resource")
	ServerSocket registryServer = new ServerSocket(registryPort);
	while(true){
		System.out.println("Listening on port 1099 ...");
		Thread.sleep(1000);
		Socket registrySocket = registryServer.accept();	
	    ObjectInputStream regSocketInput = new ObjectInputStream(registrySocket.getInputStream());
		regSocketInput.readObject();
		
	}
}

public static void main(String[] args) throws UnknownHostException{
	
	    	
		if(args.length != 1){
			System.out.println("Please enter the Arguments of the form - Registry_IP");
			
		}
		
		String Registry_IP = args[0]; 
		host = Registry_IP;
		Registry reg = new Registry(Registry_IP,registry_port);	
		System.out.println("Starting registry on host " + Registry_IP + " and port " + registry_port);
		new Thread(reg).start();
	}
	
}