package registry;
import generics.myRemoteInterface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

public class Registry implements RegistryInterface, Runnable {
	
	public final static int registry_port = 1099;
	private String host;
	private int port;
	private static ConcurrentHashMap<String, RemoteObjectRef> regMap= new ConcurrentHashMap<String, RemoteObjectRef>();

	//Constructor to instantiate rmiRegistry 
	public Registry(String ipAddress, int port){
		this.setHost(ipAddress);
		this.setPort(port);
		
	}
	
	private void setHost(String ipAddress) {
		
		
	}

	public void bind(String name, RemoteObjectRef obj) throws  AlreadyBoundException, RemoteException{
		System.out.println("Binding method Invoked...");
		if(!regMap.containsKey(name)){
			
			regMap.put(name, obj);	
			
		}
		else{
			throw new AlreadyBoundException("This name - " + name+ " is already bound");
		}
	}
	
    public String unbind(String name) throws RemoteException, NotBoundException{
    	String unboundKey = null;
    	
    	if(regMap.containsKey(name)){
			
    		unboundKey = regMap.get(name).getkeyName();
			regMap.remove(name);	
			
		}
    	else{
    		throw new NotBoundException("This name is not binded");
    	}
    return unboundKey;
    }
    
    public void rebind(String name, RemoteObjectRef obj) throws RemoteException{
   
        if(regMap.containsKey(name)){
           if(regMap.get(name).getCallee().equals("bind"))	{
        	   try {
				throw new AlreadyBoundException("This name - " + name+ " is already bound...cannot rebind");
			} catch (AlreadyBoundException e) {
				
			   }
           }
           else{
        	   System.out.println("Re-binding method Invoked...");
        	   regMap.put(name, obj);
           }
        }
        else{
        	System.out.println("Re-binding method Invoked...");
     	    regMap.put(name, obj);
        }
        	
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

	

	@Override
	public void run() {
                
			try {
				try {
					startRegistry(host, registry_port);
					
				} catch (ClassNotFoundException | InterruptedException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
   
	}
	
private void startRegistry(String host, int registryPort) throws IOException, ClassNotFoundException, InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
	@SuppressWarnings("resource")
	ServerSocket registryServer = new ServerSocket(registryPort);
	while(true){
		System.out.println("Listening on port 1099 ...");
		Thread.sleep(1000);
		Socket registrySocket = registryServer.accept();	

		ObjectInputStream regSocketInput = new ObjectInputStream(registrySocket.getInputStream());
		Registry_stub obj = (Registry_stub)regSocketInput.readObject();
		
		Object[] methodParams = new Object[]{};
		methodParams = obj.getMethodParams();
		System.out.println("MethodParams "+methodParams+" param length "+methodParams.length);
		
		Class<?>[] paramTypes = new Class[methodParams.length];
		for (int i = 0; i < methodParams.length; i++) {
		paramTypes[i] = methodParams[i].getClass();
		}
		
		Registry r = new Registry(obj.getRemoteObjectSent().getIP_adr(),obj.getRemoteObjectSent().getPort());
        Method suspending = r.getClass().getDeclaredMethod(obj.getMethodName(), paramTypes );
        suspending.setAccessible(true);
        if(methodParams.length == 0)
        	suspending.invoke(r, (Object[])null);
        else{
        	suspending.invoke(r, methodParams);
        }
        
       
	}
}

public static void main(String[] args) throws UnknownHostException{
	
	    	
		if(args.length != 1){
			System.out.println("Please enter the Arguments of the form - Registry_IP");
			
		}
		
		String Registry_IP = args[0]; 
		Registry reg = new Registry(Registry_IP,registry_port);	
		System.out.println("Starting registry on host " + Registry_IP + " and port " + registry_port);
		new Thread(reg).start();
	}
	
}