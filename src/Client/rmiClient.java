package Client;

import generics.Naming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Server.serverArithmeticInterface;

public class rmiClient implements Runnable{

	String ServerIp;
	static int ServerPort = 9999;
   public rmiClient(String ServerIp){
		
		this.ServerIp = ServerIp;
				
	}
   
   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{
//		@SuppressWarnings("resource")
//		Socket workerServer = new Socket(ServerIp,ServerPort);

		
//		while(true){
//		
//        PrintStream out = new PrintStream(workerServer.getOutputStream());
//		
//	
//		out.println("Hello ");
//		Thread.sleep(1000);
//		InputStreamReader input = new InputStreamReader(workerServer.getInputStream());
//		BufferedReader in = new BufferedReader(input);
//		out.flush();
//		
//
//		}
	  
	   
	   serverArithmeticInterface remoteObject = (serverArithmeticInterface)Naming.lookUp("rmi://127.0.	0.1:1099/test1");
	   int retVal = remoteObject.add(20, 10);
	   System.out.println("Sum of 2 numbers : "+ retVal);
	   
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