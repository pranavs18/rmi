
/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */

package registry;

import generics.Communication;
import generics.Message;
import generics.MessageType;
import generics.myRemoteInterface;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


 public class Registry_stub implements RegistryInterface,Serializable {

	
	public Registry_stub() {
	
	}
	
	private static final long serialVersionUID = 1L;

	
	String host;
	Integer port;
	String bindName;

	
	Message message;
	
	
	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public void bind(String name, RemoteObjectRef obj)
			throws AlreadyBoundException, RemoteException {	
		
		String hostPortName = this.parseHostPort(name);
		String arguments[] = null;
		arguments = hostPortName.split(" ");
		
		
		this.setHost(arguments[0]); 
		this.setPort(Integer.parseInt(arguments[1]));
		this.setBindName(arguments[2]);

		
		Object newObj[] = new Object[2];
		newObj[0] = this.getBindName();
		newObj[1] = obj;

		Class<?> thisClass = null;
		try {
			thisClass = Class.forName(this.getClass().getCanonicalName());
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
				
		Class<?>[] argTypes = new Class[newObj.length];
		for (int i = 0; i < newObj.length; i++) {
			argTypes[i] = newObj[i].getClass();
		} 

		
		Method method = null;
		try {
			method = thisClass.getMethod("bind", argTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			
			e.printStackTrace();
		}
		Class<?> returnType =method.getReturnType();
		
		Message localMessage = new Message(MessageType.METHOD, "bind", newObj, argTypes, returnType, null, null,obj,null);
		
		this.setMessage(localMessage);
		
		Communication comm = new Communication(this.getHost(), this.getPort(), this);
		Message returnMessage = comm.connect();
		if(returnMessage.getMessageType() == MessageType.EXCEPTION){
			try {
				throw new Exception(returnMessage.getException());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		returnMessage = null;
	}

	
	
	@Override
	public String unbind(String name) throws RemoteException, NotBoundException {
		
		String hostPortName = this.parseHostPort(name);
		String arguments[] = null;
		arguments = hostPortName.split(" ");
		Object strReturnMessage = null;
		
		this.setHost(arguments[0]);
		this.setPort(Integer.parseInt(arguments[1]));
		this.setBindName(arguments[2]);
		
		Object newObj[] = new Object[1];
		newObj[0] = this.getBindName();
		
		Class<?> thisClass = null;
		try {
			thisClass = Class.forName(this.getClass().getCanonicalName());
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
				
		Class<?>[] argTypes = new Class[newObj.length];
		for (int i = 0; i < newObj.length; i++) {
			argTypes[i] = newObj[i].getClass();
		} 

		
		Method method = null;
		try {
			method = thisClass.getMethod("unbind", argTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			
			e.printStackTrace();
		}
		Class<?> returnType =method.getReturnType();
		
		Message localMessage = new Message(MessageType.METHOD, "unbind", newObj, argTypes, returnType, null, null,null,null);
		
		this.setMessage(localMessage);
		
		
		Communication comm = new Communication(this.getHost(), this.getPort(), this);
		Message returnMessage = comm.connect();
		if(returnMessage.getMessageType() == MessageType.EXCEPTION){
			try {
				throw new Exception(returnMessage.getException());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(returnMessage.getMessageType() == MessageType.RETURN) {
			strReturnMessage = returnMessage.getReturnValue();
		}
		
		return strReturnMessage.toString();
	}

	@Override
	public void rebind(String name, RemoteObjectRef obj) throws RemoteException {
		
		
		String hostPortName = this.parseHostPort(name);
		String arguments[] = null;
		arguments = hostPortName.split(" ");
		
		this.setHost(arguments[0]);
	
		this.setPort(Integer.parseInt(arguments[1]));
		this.setBindName(arguments[2]);
		Object newObj[] = new Object[2];
		newObj[0] = this.getBindName();
		newObj[1] = obj;

		Class<?> thisClass = null;
		try {
			thisClass = Class.forName(this.getClass().getCanonicalName());
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
				
		Class<?>[] argTypes = new Class[newObj.length];
		for (int i = 0; i < newObj.length; i++) {
			argTypes[i] = newObj[i].getClass();
		} 

		
		Method method = null;
		try {
			method = thisClass.getMethod("rebind", argTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			
			e.printStackTrace();
		}
		Class<?> returnType =method.getReturnType();
		
		Message localMessage = new Message(MessageType.METHOD, "rebind", newObj, argTypes, returnType, null, null,obj,null);
		
		this.setMessage(localMessage);

	
		
		Communication comm = new Communication(this.getHost(), this.getPort(), this);
		Message returnMessage = comm.connect();
		if(returnMessage.getMessageType() == MessageType.EXCEPTION){
			try {
				throw new Exception(returnMessage.getException());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		returnMessage = null;
	}

	

@Override
public myRemoteInterface lookUp(String name) {
	
	String hostPortName = this.parseHostPort(name);
	String arguments[] = null;
	arguments = hostPortName.split(" ");
	
	this.setHost(arguments[0]);
	this.setPort(Integer.parseInt(arguments[1]));
	this.setBindName(arguments[2]);
	
	Object newObj[] = new Object[1];
	newObj[0] = this.getBindName();

	Class<?> thisClass = null;
	try {
		thisClass = Class.forName(this.getClass().getCanonicalName());
	} catch (ClassNotFoundException e1) {
		
		e1.printStackTrace();
	}
	
			
	Class<?>[] argTypes = new Class[newObj.length];
	for (int i = 0; i < newObj.length; i++) {
		argTypes[i] = newObj[i].getClass();
	} 

	
	Method method = null;
	try {
		method = thisClass.getMethod("lookUp", argTypes);
	} catch (NoSuchMethodException | SecurityException e) {
		
		e.printStackTrace();
	}
	Class<?> returnType =method.getReturnType();
	
	Message localMessage = new Message(MessageType.METHOD, "lookUp", newObj, argTypes, returnType, null, null,null,null);
	
	this.setMessage(localMessage);


	
	Communication comm = new Communication(this.getHost(), this.getPort(), this);
	Message returnMessage = comm.connect();
	RemoteObjectRef ref = null;
	
	
	if(returnMessage.getMessageType() == MessageType.EXCEPTION){
		try {
			throw new Exception(returnMessage.getException());
		} catch (Exception e) {
			System.out.println("Exception returned ");
			e.printStackTrace();
		}
	}
	
	else if(returnMessage.getMessageType() == MessageType.RETURN) {
		ref = (RemoteObjectRef) returnMessage.getReturnValue();
	}

	String constructorArgument =  this.getBindName();
	RemoteObjectRef sendRor = ref;
	
	Object[] x = {constructorArgument,sendRor};
	
	
	Class<?> stubClass = null;
	myRemoteInterface myinterfaceObject =null;
	try {
		stubClass = Class.forName(ref.getClass_Name()+"_stub");
		Constructor<?> constructor = stubClass.getConstructor(String.class,RemoteObjectRef.class);
		myinterfaceObject = (myRemoteInterface)constructor.newInstance(x);
	} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		e.printStackTrace();
	}


	
	return myinterfaceObject;
	
}

@SuppressWarnings("unchecked")
@Override
public ArrayList<String> listObjects(String name) {
	String hostPortName = this.parseHostPort(name);
	String arguments[] = null;
	arguments = hostPortName.split(" ");
	this.setHost(arguments[0]);
	this.setPort(Integer.parseInt(arguments[1]));
	
	Class<?> thisClass = null;
	try {
		thisClass = Class.forName(this.getClass().getCanonicalName());
	} catch (ClassNotFoundException e1) {
		
		e1.printStackTrace();
	}
	
	Object newObj[] = new Object[1];
	newObj[0] = name;
	
	Class<?>[] argTypes = new Class[newObj.length];
	for (int i = 0; i < newObj.length; i++) {
		argTypes[i] = newObj[i].getClass();
	} 

	Method method = null;
	try {
		method = thisClass.getMethod("listObjects", argTypes);
	} catch (NoSuchMethodException | SecurityException e) {
		
		e.printStackTrace();
	}
	Class<?> returnType =method.getReturnType();
	
	Message localMessage = new Message(MessageType.METHOD, "listObjects", newObj, argTypes, returnType, null, null,null,null);
	
	this.setMessage(localMessage);


	
	Communication comm = new Communication(this.getHost(), this.getPort(), this);
	Message returnMessage = comm.connect();
	ArrayList<String> ref = null;
	
	
	if(returnMessage.getMessageType() == MessageType.EXCEPTION){
		try {
			throw new Exception(returnMessage.getException());
		} catch (Exception e) {
			System.out.println("Exception returned ");
			e.printStackTrace();
		}
	}
	
	else if(returnMessage.getMessageType() == MessageType.RETURN) {
		ref = (ArrayList<String>) returnMessage.getReturnValue();
	}
	
	return ref;
}
	


public String parseHostPort(String fullName){
	
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

}
