package registry;
import generics.*;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class rmiRegistry extends Thread implements myRemoteInterface {
	
	
	private String bindName;
	private int port;
	//Constructor to instantiate rmiRegistry 
	public rmiRegistry(String bindName, int port){
		this.bindName = bindName;
		this.port = port;
	}
	
	
	
}