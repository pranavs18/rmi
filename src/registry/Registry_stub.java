package registry;

import generics.Communication;
import generics.Message;
import generics.MessageType;

import java.io.Serializable;
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
		
		Message localMessage = new Message(MessageType.METHOD, "bind", newObj, argTypes, returnType, null, null,obj);
		
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
		String strReturnMessage = null;
		
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
		
		Message localMessage = new Message(MessageType.METHOD, "unbind", newObj, argTypes, returnType, null, null,null);
		
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
			strReturnMessage = returnMessage.getReturnValue().toString();
		}
		
		return strReturnMessage;
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
		
		Message localMessage = new Message(MessageType.METHOD, "rebind", newObj, argTypes, returnType, null, null,obj);
		
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

public String parseHostPort(String fullName){
			
		String newName = fullName.trim();
		int indexOfColon = newName.indexOf(":");
		int indexOfSlash = newName.indexOf("/", indexOfColon);
		String host = newName.substring(2, indexOfColon);
		String port = newName.substring(indexOfColon+1,indexOfSlash);
		String name = newName.substring(indexOfSlash+1);
		String finalString = host+" "+port+" "+name;
		return finalString;
	}

@Override
public RemoteObjectRef lookUP(String name) {
	return null;
}

@Override
public ArrayList<String> listObjects() {
	return null;
}
		
}
