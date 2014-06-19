package generics;

import registry.RemoteException;

public interface ConcatenationInterface extends myRemoteInterface{

	String concat(String a, String b) throws RemoteException;
}
