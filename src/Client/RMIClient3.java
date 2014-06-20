package Client;

import generics.ConcatenationInterface;
import generics.FinalCompilerTestInterface;
import generics.Naming;
import generics.serverArithmeticInterface;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RMIClient3 implements Runnable{

		String ServerIp;
		static int ServerPort = 9999;
	   public RMIClient3(String ServerIp){
			
			this.ServerIp = ServerIp;
					
		}
	   
	   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

		  
		   FinalCompilerTestInterface myfinalTest = (FinalCompilerTestInterface)Naming.lookUp("rmi://128.237.191.229:1099/finaltest");
		   String retVal = null;
		try {
			retVal = myfinalTest.hello();
			System.out.println("Concat value : "+ retVal);
			
			ArrayList<Integer> result1 = myfinalTest.arrayWorld(100);
			System.out.println(result1);
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
		
			RMIClient3 client = new RMIClient3(ServerIp);
			
			// Starts worker host thread
			new Thread(client).start();
			
		}

	
}
