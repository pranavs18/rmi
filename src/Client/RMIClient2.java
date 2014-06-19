package Client;

import generics.ConcatenationInterface;
import generics.Naming;
import generics.serverArithmeticInterface;

import java.io.IOException;
import java.net.UnknownHostException;

public class RMIClient2 implements Runnable{

		String ServerIp;
		static int ServerPort = 9999;
	   public RMIClient2(String ServerIp){
			
			this.ServerIp = ServerIp;
					
		}
	   
	   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

		   ConcatenationInterface remoteObject = (ConcatenationInterface)Naming.lookUp("rmi://127.0.0.1:1099/test2");
		   String retVal = null;
		try {
			retVal = remoteObject.concat("Vaibhav", "+Kumar");
			System.out.println("Concat value : "+ retVal);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		   
	}
		@Override
		public void run() {
			try {
				startrmiClient(ServerIp,ServerPort);
			} catch (UnknownHostException e) {	
				e.printStackTrace();
			} catch (IOException e) {			
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	public static void main(String[] args){
			
			
			String ServerIp = args[0];		
		
			RMIClient2 client = new RMIClient2(ServerIp);
			
			// Starts worker host thread
			new Thread(client).start();
			
		}

	
}
