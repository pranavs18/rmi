package Server;

import generics.ClientServerCommunication;
import generics.Message;
import generics.MessageType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import registry.RemoteObjectRef;

public class serverArithmetic_stub implements serverArithmeticInterface, Serializable {

	private static final long serialVersionUID = 1L;
    private int firstArgument;
    private int secondArgument;
    String host ;
	Integer port;
    Message message;
    String lookupName;
    RemoteObjectRef ror;
    
    public RemoteObjectRef getRor() {
		return ror;
	}
	public serverArithmetic_stub(){
    	
    }
    public serverArithmetic_stub(String lookupName, RemoteObjectRef ror){
    	this.lookupName = lookupName;
    	this.ror = ror;
    }
    
    public String getLookupName() {
		return lookupName;
	}


	public Message getMessage() {
		return message;
	}
   
    public void setHost(String host) {
	
			this.host = host;
		
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
    
	public void setMessage(Message message) {
		this.message = message;
	}
	
	
	@Override
	public int divide(int firstNumber, int secondNumber) {
		
		System.out.println("Boooya Booyah" + this.getLookupName());
		this.firstArgument = firstNumber;
		this.secondArgument = secondNumber;
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
		newObj[0] = this.firstArgument;
		newObj[1] = this.secondArgument;
		
		Class<?>[] argTypes = new Class[newObj.length];
		for (int i = 0; i < newObj.length; i++) {
			
			argTypes[i] = newObj[i].getClass();
			if(!argTypes[i].isPrimitive()){
				argTypes[i]= int.class;
			}
			System.out.println(argTypes[i]);
		} 
       
		
		try {
			method = thisClass.getMethod("divide", argTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			
			e.printStackTrace();
		}
		Class<?> returnType =method.getReturnType();
		
		Message localMessage = new Message(MessageType.METHOD, "divide", newObj, argTypes, returnType, null, null,this.getRor(),this.getLookupName());
		
		this.setMessage(localMessage);
		System.out.println("Host port "+this.host+" "+this.port);
		ClientServerCommunication comm = new ClientServerCommunication(this.getHost(), this.getPort(), this.getMessage());
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
			return (int)retValue;
		}
		
		return 0;
		
	}

	private Integer getPort() {
		return this.port;
	}

	private String getHost() {
		return this.host;
	}

	@Override
	public int multiply(int firstNumber, int secondNumber) {
		
		return 0;
	}

	@Override
	public int subtract(int firstNumber, int secondNumber) {
		
		return 0;
	}

	@Override
	public int add(int firstNumber, int secondNumber) {
		
		return 0;
	}
	
	
	
}