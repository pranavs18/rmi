package Server;


import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;




import java.util.Map.Entry;

import generics.Naming;
import registry.AlreadyBoundException;
import registry.NotBoundException;
import registry.RemoteException;
import registry.RemoteObjectRef;

public class remoteServer implements Runnable,Serializable{
	

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) throws IOException, NotBoundException{
		
	
		serverArithmetic serAr = new serverArithmetic();
		ConcatenationImpl concat = new ConcatenationImpl();
		try {
			
		 Naming.bind("//127.0.0.1:1099/test1", serAr);
		 Naming.rebind("//127.0.0.1:1099/test2", concat);
		
		} catch (AlreadyBoundException | RemoteException e) {
			
			e.printStackTrace();
		}

		remoteServer s = new remoteServer();
		new Thread(s).start();
		
	}
  
	
	

	@Override
	public void run() {
		
		 ConnectClient clients = new ConnectClient();
		 try {
			clients.createConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
	
