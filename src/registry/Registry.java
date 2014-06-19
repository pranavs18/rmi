package registry;
import generics.Message;
import generics.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Registry implements RegistryInterface, Runnable {
	
	public final static int registry_port = 1099;
	private String host;
	private static ConcurrentHashMap<String, RemoteObjectRef> regMap= new ConcurrentHashMap<String, RemoteObjectRef>();

	
	public Registry(){
		
	}
	
	//Constructor to instantiate rmiRegistry 
	public Registry(String ipAddress, int port){
		this.setHost(ipAddress);
		this.setPort(port);
		
	}


	public void bind(String name, RemoteObjectRef obj) throws AlreadyBoundException {
		System.out.println("Binding method Invoked...");
		
		if(!regMap.containsKey(name)){
			
			regMap.put(name, obj);	
			
		}
		else{
			throw new AlreadyBoundException("This name - " + name+ " is already bound");
			
		}
		
	}
	
    public String unbind(String name) throws RemoteException, NotBoundException{
    	System.out.println("Unbind method invoked");
    	
    	String unboundKey = null;
    	RemoteObjectRef ror = null;
    	
    	if(regMap.containsKey(name)){
    		ror = regMap.get(name);
    		
    		unboundKey = ror.getkeyName();
			regMap.remove(name);	
				
		}
    	else{
    		throw new NotBoundException("This name is not binded");
    	}
    	
    return unboundKey;
    }
    
    public void rebind(String name, RemoteObjectRef obj) throws RemoteException, AlreadyBoundException{
   
        if(regMap.containsKey(name)){
           if(regMap.get(name).getCallee().equals("bind"))	{
        	   
				throw new AlreadyBoundException("This name - " + name+ " is already bound...cannot rebind");

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
    
    
    @Override
	public RemoteObjectRef lookUp(String name) {
		System.out.println("Lookup invoked with name "+ name);
    	if(regMap.containsKey(name)){	
		RemoteObjectRef value = regMap.get(name);
		return value;
		}
    	else{
    	System.out.println("Null invoked");
		return null;
    	}
	}

	@Override
	public ArrayList<String> listObjects() {
		ArrayList<String> al = new ArrayList<String>();
		for(Entry<String,RemoteObjectRef> e: regMap.entrySet()){
		al.add(e.getKey());
		}
		return al;
	}

	

	@Override
	public void run() {
                
			try {
				try {
					startRegistry(host, registry_port);
					
				} catch (ClassNotFoundException | InterruptedException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
   
	}
	
private void startRegistry(String host, int registryPort) throws IOException, ClassNotFoundException, InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException {
		
	@SuppressWarnings("resource")
	ServerSocket registryServer = new ServerSocket(registryPort);
	while(true){
		System.out.println("Listening on port 1099 ...");
		Thread.sleep(1000);
		Socket registrySocket = registryServer.accept();	

		ObjectInputStream regSocketInput = new ObjectInputStream(registrySocket.getInputStream());
		RegistryInterface obj = (Registry_stub) regSocketInput.readObject();

		Registry r = new Registry();
		
        Method suspending = r.getClass().getDeclaredMethod(obj.getMessage().getMethodName(), obj.getMessage().getArgTypes() );
        

        Object returnValue;
        Message returnMessage;
        
        if(obj.getMessage().getArguments().length == 0){
			try {
				suspending.setAccessible(true);
				returnValue = suspending.invoke(r, (Object[])null);
				returnMessage = new Message(MessageType.RETURN, obj.getMessage().getMethodName(), obj.getMessage().getArguments(), obj.getMessage().getArgTypes(), obj.getMessage().getReturnType(), returnValue, null, obj.getMessage().getRor(),null);
			} catch (InvocationTargetException e) {		
				Throwable cause = e.getCause();
					returnMessage = new Message(MessageType.EXCEPTION, obj.getMessage().getMethodName(), obj.getMessage().getArguments(), obj.getMessage().getArgTypes(), obj.getMessage().getReturnType(), null, cause.getMessage(), obj.getMessage().getRor(),null);
			}
        }
		else{
        	try {
        		suspending.setAccessible(true);
				returnValue = suspending.invoke(r, obj.getMessage().getArguments());
				
				returnMessage = new Message(MessageType.RETURN, obj.getMessage().getMethodName(), obj.getMessage().getArguments(), obj.getMessage().getArgTypes(), obj.getMessage().getReturnType(), returnValue, null, obj.getMessage().getRor(),null);
				
				Registry_stub ret = new Registry_stub();
				ret.setBindName(null);
				ret.setHost(null);
				ret.setPort(null);
				ret.setMessage(returnMessage);
				
				ObjectOutputStream oos = new ObjectOutputStream(registrySocket.getOutputStream());
				oos.writeObject(ret);
				oos.close();
				
				
				
			} catch (InvocationTargetException e) {
				
				Throwable cause = e.getCause();
				returnMessage = new Message(MessageType.EXCEPTION, obj.getMessage().getMethodName(), obj.getMessage().getArguments(), obj.getMessage().getArgTypes(), obj.getMessage().getReturnType(), null, cause.getMessage(), obj.getMessage().getRor(),null);
				
				Registry_stub ret = new Registry_stub();
				ret.setBindName(null);
				ret.setHost(null);
				ret.setPort(null);
				ret.setMessage(returnMessage);
				
				ObjectOutputStream oos = new ObjectOutputStream(registrySocket.getOutputStream());
				oos.writeObject(ret);
				oos.close();
				
				
			}
        	
        	System.out.println(regMap);
        	
        	regSocketInput.close();	
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


@Override
public String getBindName() {
	
	return null;
}


@Override
public void setBindName(String bindName) {
	
}


@Override
public String getHost() {
	return null;
}


@Override
public void setHost(String host) {
	
}


@Override
public Integer getPort() {
	return null;
}


@Override
public void setPort(Integer port) {
}


@Override
public Message getMessage() {
	return null;
}


@Override
public void setMessage(Message message) {
	
}
	
}