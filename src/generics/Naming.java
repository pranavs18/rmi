package generics;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import registry.AlreadyBoundException;
import registry.NotBoundException;
import registry.RegistryInterface;
import registry.RemoteException;
import registry.RemoteObjectRef;

public class Naming{

	public static void bind(String name, myRemoteInterface obj) throws AlreadyBoundException, RemoteException, IOException {
		String className = obj.getClass().getCanonicalName();
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
                        System.out.println("IP ADDRESS PASSED - "+ ip);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		String nameWithSpace = Naming.parseHostPort(name);
		String args[] = nameWithSpace.split(" ");
		
		RemoteObjectRef newRef = new RemoteObjectRef(ip, 9999,args[2] , className,"bind");
		
		ObjectMap.insertIntoServerMap(args[2], obj);
		
		System.out.println("Noooooooo "+obj.getClass().getCanonicalName()); //Remove
		
		RegistryInterface stub = null;
		 try{
			 
			 Class<?> stub_class = Class.forName("registry.Registry_stub");
			 stub = (RegistryInterface)Class.forName("registry.Registry_stub").newInstance();
			 if(stub_class != null){
				 byte[] temp = downloadToServer(stub_class, newRef);
			 }
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		stub.bind(name, newRef);
		
	}

	public static void unbind(String name) throws RemoteException, NotBoundException {
		
		
		RegistryInterface stub = null;
		 try {
			stub = (RegistryInterface)Class.forName("registry.Registry_stub").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		String retVal = stub.unbind(name);
		ObjectMap.removeFromServerMap(retVal);
		
		
		
	}

	public static void rebind(String name, myRemoteInterface obj) throws RemoteException, AlreadyBoundException {
	
		String className = obj.getClass().getCanonicalName();
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
                        System.out.println("IP ADDRESS PASSED " + ip);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		String nameWithSpace = Naming.parseHostPort(name);
		String args[] = nameWithSpace.split(" ");
		
		RemoteObjectRef newRef = new RemoteObjectRef(ip, 9999,args[2] , className,"rebind");
		
		ObjectMap.insertIntoServerMap(args[2], obj);
		
		System.out.println("Noooooooo "+obj.getClass().getCanonicalName()); //Remove
		
		RegistryInterface stub = null;
		 try{
			 
			 Class<?> stub_class = Class.forName("registry.Registry_stub");
			 stub = (RegistryInterface)Class.forName("registry.Registry_stub").newInstance();
			 if(stub_class != null){
				 byte[] temp = downloadToServer(stub_class, newRef);
			 }
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		stub.rebind(name, newRef);
		
		
	}
	
	public static myRemoteInterface lookUp(String name){
	
		RegistryInterface stub = null;
		try{
			stub = (RegistryInterface)Class.forName("registry.Registry_stub").newInstance();
		}catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return (myRemoteInterface)stub.lookUp(name);
		
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> listObjects(String name){
		RegistryInterface stub = null;
		try{
			stub = (RegistryInterface)Class.forName("registry.Registry_stub").newInstance();
		}catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return (ArrayList<String>)stub.listObjects(name);
			
	}
	
	public static String parseHostPort(String fullName){
		
		String newName = fullName.trim();
		String name = "";
		int indexOfSlash1 = newName.indexOf("/");
		int indexOfColon = newName.indexOf(":",indexOfSlash1);
		int indexOfSlash2 = newName.indexOf("/", indexOfColon);
		String host = newName.substring(indexOfSlash1+2, indexOfColon);
		String port = newName.substring(indexOfColon+1,indexOfSlash2);
		if(indexOfSlash2 +1 < fullName.length())
			  name = newName.substring(indexOfSlash2+1);
		String finalString = host+" "+port+" "+name;
		return finalString;
	}

	public static byte[] downloadToServer(Class<?> className, RemoteObjectRef obj){
		byte[] temp = null;
	/*	String name = className.getName();
		System.out.println("Class file to be downloaded is :" + name);
		String genericClassPathFormat = name.replaceAll("\\.", "/") + ".class";
		String httpURL = "http://" + obj.getIP_adr() + ":" + obj.getPort() + "/Registry" + genericClassPathFormat;*/
		return temp;
	}
 
	
}
