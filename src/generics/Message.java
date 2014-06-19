package generics;

import java.io.Serializable;

import registry.RemoteObjectRef;

public class Message implements Serializable {
	

	private static final long serialVersionUID = 1L;

	MessageType messageType = null;
	String methodName = null;
	Object[] arguments = new Object[]{};
	Class<?>[] argTypes = new Class<?>[]{};
	Class<?> returnType = null;
	Object returnValue = null;
	String exception = null;
	RemoteObjectRef ror =null;
	String lookupName = null;
	
	

	public Message(MessageType messageType, String methodName,
			Object[] arguments, Class<?>[] argTypes, Class<?> returnType, Object returnValue,
			String exception,RemoteObjectRef ror, String lookupName) {
		super();
		this.messageType = messageType;
		this.methodName = methodName;
		this.arguments = arguments;
		this.argTypes = argTypes;
		this.returnType = returnType;
		this.exception = exception;
		this.returnValue = returnValue;
		this.ror =ror;
		this.lookupName = lookupName;
	}



	public String getLookupName() {
		return lookupName;
	}



	public RemoteObjectRef getRor() {
		return ror;
	}



	public Object getReturnValue() {
		return returnValue;
	}



	public MessageType getMessageType() {
		return messageType;
	}



	public String getMethodName() {
		return methodName;
	}



	public Object[] getArguments() {
		return arguments;
	}



	public Class<?>[] getArgTypes() {
		return argTypes;
	}



	public Class<?> getReturnType() {
		return returnType;
	}



	public String getException() {
		return exception;
	}

}