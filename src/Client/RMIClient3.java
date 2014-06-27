/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */

package Client;

import generics.ConcatenationInterface;
import generics.FinalCompilerTestInterface;
import generics.MixbagTestInterface;
import generics.Naming;
import generics.serverArithmeticInterface;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class RMIClient3 implements Runnable{

		String ServerIp;
		static int ServerPort = 9999;
	   public RMIClient3(String ServerIp){
			
			this.ServerIp = ServerIp;
					
		}
	   
	   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

		  
		  MixbagTestInterface mix = (MixbagTestInterface)Naming.lookUp("rmi://"+ServerIp+":1099/mix");
		  
		  mix.hello();
		  Float result = mix.divide(100F, 2.0F, 3.0F);
		  System.out.println("Float result= "+result);
		  
		  HashMap<String,Integer> hamap = new HashMap<String, Integer>();
		  hamap = mix.has("Vsk", 1);
		  System.out.println("hashmap result= "+hamap);
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
