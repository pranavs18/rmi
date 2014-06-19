package Server;

import generics.ConcatenationInterface;
import registry.RemoteException;


public class ConcatenationImpl implements ConcatenationInterface {

	@Override
	public String concat(String a, String b) throws RemoteException {
		return a.concat(b);
		
	}
	
}
