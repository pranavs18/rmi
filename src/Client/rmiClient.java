package Client;

import generics.Naming;

import java.io.IOException;
import java.net.UnknownHostException;

import Server.serverArithmeticInterface;

public class rmiClient implements Runnable{

	String ServerIp;
	static int ServerPort = 9999;
   public rmiClient(String ServerIp){
		
		this.ServerIp = ServerIp;
				
	}
   
   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

	   serverArithmeticInterface remoteObject = (serverArithmeticInterface)Naming.lookUp("rmi://127.0.0.1:1099/test1");
	   int retVal = 0;
	try {
		retVal = remoteObject.divide(20, 3);
		System.out.println("Division of 2 numbers : "+ retVal);
	} catch (Exception e) {
		// TODO Auto-generated catch block
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
	
		rmiClient client = new rmiClient(ServerIp);
		
		// Starts worker host thread
		new Thread(client).start();
		
	}
	
}