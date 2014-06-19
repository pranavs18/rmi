package Server;

import generics.Communication;
import generics.ConcatenationInterface;
import generics.Message;
import generics.MessageType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.RemoteException;
import registry.RemoteObjectRef;

public class ConcatenationImpl_stub implements  ConcatenationInterface,Serializable{



	private static final long serialVersionUID = 1L;
	String host ;
	Integer port;
	Message message;
	String lookupName;
	RemoteObjectRef ror;
	
	public ConcatenationImpl_stub(){

	}
	public ConcatenationImpl_stub(String lookupName, RemoteObjectRef ror){
	this.lookupName = lookupName;
	this.ror = ror;
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
	public String getLookupName() {
		return lookupName;
	}
	public void setLookupName(String lookupName) {
		this.lookupName = lookupName;
	}
	public RemoteObjectRef getRor() {
		return ror;
	}
	public void setRor(RemoteObjectRef ror) {
		this.ror = ror;
	}
	
	
	@Override
	public String concat(String a, String b) throws RemoteException {
		
		try {
			this.setHost(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e2) {
			e2.printStackTrace();
			}
			this.setPort(9999);

			Object retValue = null;
			Class<?> thisClass = null;
			try {
			thisClass = Class.forName(this.getClass().getCanonicalName());
			} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
			}
			Method method = null;
			Object newObj[] = new Object[2];
			newObj[0] = a;
			newObj[1] = b;

			Class<?>[] argTypes = new Class[newObj.length];
			for (int i = 0; i < newObj.length; i++) {

			argTypes[i] = newObj[i].getClass();
			if(argTypes[i].isPrimitive()){
			argTypes[i]= int.class;
			System.out.println("haha");
			}
			} 

			try {
			method = thisClass.getMethod("concat", argTypes);
			} catch (NoSuchMethodException | SecurityException e) {

			e.printStackTrace();
			}
			Class<?> returnType =method.getReturnType();

			Message localMessage = new Message(MessageType.METHOD, "concat", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());

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
			retValue = returnMessage.getReturnValue();
			return (String)retValue;
			}

			return null;
	}



}
