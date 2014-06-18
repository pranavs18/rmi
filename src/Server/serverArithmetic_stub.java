package Server;

import generics.Communication;
import generics.Message;
import generics.MessageType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class serverArithmetic_stub implements serverArithmeticInterface, Serializable {

	private static final long serialVersionUID = 1L;
    private int firstArgument;
    private int secondArgument;
    String host ;
	Integer port;
    Message message;
    
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
	public int add(int firstNumber, int secondNumber) {
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
			method = thisClass.getMethod("add", argTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			
			e.printStackTrace();
		}
		Class<?> returnType =method.getReturnType();
		
		Message localMessage = new Message(MessageType.METHOD, "add", newObj, argTypes, returnType, null, null,null);
		
		this.setMessage(localMessage);
		System.out.println("Host port "+this.host+" "+this.port);
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
			retValue = returnMessage.getReturnValue().toString();
		}
		
		return (int)retValue;
		
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
	public int divide(int firstNumber, int secondNumber) {
		
		return 0;
	}
	
	
	
}