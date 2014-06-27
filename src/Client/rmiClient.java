package Client;

import generics.Naming;
import generics.serverArithmeticInterface;

import java.io.IOException;
import java.net.UnknownHostException;

public class rmiClient implements Runnable{

   String serverIp;
   static int ServerPort = 9999;
   public rmiClient(String serverIp){
           this.serverIp = serverIp;		
				
	}
   
   public void startrmiClient(String ServerIp) throws UnknownHostException, IOException, InterruptedException{

	   serverArithmeticInterface remoteObject = (serverArithmeticInterface)Naming.lookUp("rmi://"+ ServerIp +":1099/test1");
	   int retVal = 0;
	try {
		retVal = remoteObject.divide(20, 0);
		//System.out.println("Division of 2 numbers : "+ retVal);
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
			startrmiClient(serverIp);
		} catch (UnknownHostException e) {	
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
public static void main(String[] args){
		
		
            if(args.length != 1){
                 System.out.println("Please enter the registry IP here");
             }
	
		rmiClient client = new rmiClient(args[0]);
		
		// Starts worker host thread
		new Thread(client).start();
		
	}
	
}
