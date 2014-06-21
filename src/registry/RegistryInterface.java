package registry;

import java.util.ArrayList;

import generics.Message;
import generics.myRemoteInterface;

public interface RegistryInterface extends myRemoteInterface{

    public void bind(String name, RemoteObjectRef obj) throws  AlreadyBoundException, RemoteException;
    public String unbind(String name) throws RemoteException, NotBoundException;
    public void rebind(String name, RemoteObjectRef obj) throws RemoteException, AlreadyBoundException;
    
    public String getBindName();
	public void setBindName(String bindName);
	public String getHost();
	public void setHost(String host);
	public Integer getPort();
	public void setPort(Integer port);
	public Message getMessage();
	public void setMessage(Message message);
	
	public myRemoteInterface lookUp(String name);
	public ArrayList<String> listObjects(String name);
	
	
    
}