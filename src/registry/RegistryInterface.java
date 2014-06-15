package registry;

import generics.myRemoteInterface;

public interface RegistryInterface extends myRemoteInterface{

    void bind(String name, RemoteObjectRef obj) throws  AlreadyBoundException, RemoteException;
    void unbind(String name) throws RemoteException, NotBoundException;
    void rebind(String name, RemoteObjectRef obj) throws RemoteException;

}