package generics;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Server.ObjectMap;
import registry.AlreadyBoundException;
import registry.NotBoundException;
import registry.RegistryInterface;
import registry.RemoteException;
import registry.RemoteObjectRef;

public class Naming{

	public static void bind(String name, myRemoteInterface obj) throws AlreadyBoundException, RemoteException, IOException {
		String className = obj.getClass().getName();
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		RemoteObjectRef newRef = new RemoteObjectRef(ip, 9999, obj.toString(), className,"bind");
		
		ObjectMap.insertIntoServerMap(obj.toString(), obj);
		
		RegistryInterface stub = null;
		 try{
			 
			 Class<?> stub_class = Class.forName("registry.Registry_stub");
			 System.out.println(stub_class);
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
		stub.unbind(name);
		
		
		
	}

	public static void rebind(String name, myRemoteInterface obj) throws RemoteException {
	
		String className = obj.getClass().getName();
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		RemoteObjectRef newRef = new RemoteObjectRef(ip, 9999, obj.toString(), className,"rebind");
		
		ObjectMap.insertIntoServerMap(obj.toString(), obj);
		
		RegistryInterface stub = null;
		 try {
			stub = (RegistryInterface)Class.forName("registry.Registry_stub").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		stub.rebind(name, newRef);
		
		
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
