package registry;

public class Registry_stub implements RegistryInterface {

	String methodName;
	RemoteObjectRef remoteObjectSent;
	String host;
	Integer port;
	
	@Override
	public void bind(String name, RemoteObjectRef obj)
			throws AlreadyBoundException, RemoteException {
		
		
		
		
	}

	@Override
	public void unbind(String name) throws RemoteException, NotBoundException {
		
		
	}

	@Override
	public void rebind(String name, RemoteObjectRef obj) throws RemoteException {
		
		
	}

	
	
	
	
}
