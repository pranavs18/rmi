package generics;

import java.io.Serializable;

import registry.RemoteException;

public interface ConcatenationInterface extends myRemoteInterface{

	String concat(String a, String b) throws RemoteException;
	String concatWithInt(String a,int b);
}
