package registry;

import generics.Message;
import generics.myRemoteInterface;

public interface RegistryInterface extends myRemoteInterface{

    void bind(String name, RemoteObjectRef obj) throws  AlreadyBoundException, RemoteException;
    String unbind(String name) throws RemoteException, NotBoundException;
    void rebind(String name, RemoteObjectRef obj) throws RemoteException, AlreadyBoundException;
    
    public String getBindName();
	public void setBindName(String bindName);
	public String getHost();
	public void setHost(String host);
	public Integer getPort();
	public void setPort(Integer port);
	public Message getMessage();
	public void setMessage(Message message);
	
	
    
}