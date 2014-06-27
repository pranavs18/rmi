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
		
                if(args.length != 1){
			System.out.println("Please enter the Arguments of the form - Registry_IP");
						
		}	
		serverArithmetic serAr = new serverArithmetic();
		ConcatenationImpl concat = new ConcatenationImpl();
		FinalCompilerTest finalTest = new FinalCompilerTest();
		MixBagTest mix = new MixBagTest();
		findMaxElement max = new findMaxElement();
		try {
			
		 Naming.bind("//"+args[0]+":1099/test1", serAr);
		 Naming.rebind("//"+args[0]+":1099/test2", concat);
		 Naming.rebind("//"+args[0]+":1099/finaltest", finalTest);
		 Naming.rebind("//"+args[0]+":1099/mix", mix);
		 Naming.rebind("//"+args[0]+":1099/max", max);
		
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
	
