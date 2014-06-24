package Client;

import generics.Naming;
import generics.serverArithmeticInterface;

import java.io.IOException;
import java.net.UnknownHostException;

public class rmiClient implements Runnable{

	String ServerIp;
	static int ServerPort = 9999;
   public rmiClient(String ServerIp){
		
		this.ServerIp = ServerIp;
				
	}
   
   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

	   serverArithmeticInterface remoteObject = (serverArithmeticInterface)Naming.lookUp("rmi://128.2.13.144:1099/test1");
	   int retVal = 0;
	try {
		retVal = remoteObject.divide(20, 0);
		System.out.println("Division of 2 numbers : "+ retVal);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	  
	try {
		retVal = remoteObject.add(20, 3);
		System.out.println("addition of 2 numbers : "+ retVal);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	  
	try {
		retVal = remoteObject.multiply(20, 3);
		System.out.println("multiplication of 2 numbers : "+ retVal);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	  
	try {
		retVal = remoteObject.subtract(20, 3);
		System.out.println("subtraction of 2 numbers : "+ retVal);
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
	
		rmiClient client = new rmiClient(ServerIp);
		
		// Starts worker host thread
		new Thread(client).start();
		
	}
	
}
