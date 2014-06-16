package Server;

import generics.Communication;

import java.io.Serializable;

import registry.AlreadyBoundException;
import registry.NotBoundException;
import registry.RegistryInterface;
import registry.RemoteException;
import registry.RemoteObjectRef;

public class Registry_stub implements RegistryInterface,Serializable {

	
	public Registry_stub() {
	
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String methodName;
	RemoteObjectRef remoteObjectSent;
	String host;
	Integer port;
	String bindName;
	
	
	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public RemoteObjectRef getRemoteObjectSent() {
		return remoteObjectSent;
	}

	public void setRemoteObjectSent(RemoteObjectRef remoteObjectSent) {
		this.remoteObjectSent = remoteObjectSent;
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

	
	@Override
	public void bind(String name, RemoteObjectRef obj)
			throws AlreadyBoundException, RemoteException {	
		
		String hostPortName = this.parseHostPort(name);
		String arguments[] = null;
		arguments = hostPortName.split(" ");
		this.setHost(arguments[0]);
		this.setMethodName("bind");
		this.setPort(Integer.parseInt(arguments[1]));
		this.setBindName(arguments[2]);
		this.setRemoteObjectSent(obj);
		Communication comm = new Communication(this.getHost(), this.getPort(), this);
		comm.connect();
		
		
	}

	@Override
	public String unbind(String name) throws RemoteException, NotBoundException {
		
		String hostPortName = this.parseHostPort(name);
		String arguments[] = null;
		arguments = hostPortName.split(" ");
		this.setHost(arguments[0]);
		this.setMethodName("unbind");
		this.setPort(Integer.parseInt(arguments[1]));
		this.setBindName(arguments[2]);
		this.setRemoteObjectSent(null);
		Communication comm = new Communication(this.getHost(), this.getPort(), this);
		comm.connect();
		return null;
		
	}

	@Override
	public void rebind(String name, RemoteObjectRef obj) throws RemoteException {
		
		String hostPortName = this.parseHostPort(name);
		String arguments[] = null;
		arguments = hostPortName.split(" ");
		this.setHost(arguments[0]);
		this.setMethodName("rebind");
		this.setPort(Integer.parseInt(arguments[1]));
		this.setBindName(arguments[2]);
		this.setRemoteObjectSent(obj);
		Communication comm = new Communication(this.getHost(), this.getPort(), this);
		comm.connect();
		
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
	
	
	
	
}
