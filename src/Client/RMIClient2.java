/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */


package Client;

import generics.ConcatenationInterface;
import generics.Naming;
import generics.serverArithmeticInterface;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.UnknownHostException;

public class RMIClient2 implements Runnable{

		String ServerIp;
		static int ServerPort = 9999;
	   public RMIClient2(String ServerIp){
			
			this.ServerIp = ServerIp;
					
		}
	   
	   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

		   serverArithmeticInterface remoteObject = (serverArithmeticInterface)Naming.lookUp("rmi://"+ServerIp + ":1099/test1");
		   ConcatenationInterface c = (ConcatenationInterface)Naming.lookUp("rmi://"+ServerIp + ":1099/test2");
		   String retVal = null;
		try {
			retVal = c.concatWithInt("Hello", 4);
			System.out.println("Concat value : "+ retVal);
			int result = 0;
			result = remoteObject.add((int)1000, (int)200);
			System.out.println(result);
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
 
        public static boolean validateIP(String ip){

		   final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
		   Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
                   return IPV4_PATTERN.matcher(ip).matches();
		   
		}
		
	public static void main(String[] args){
	
	           boolean isIP = validateIP(args[0]);
	           RMIClient2 client = null;
	           if(args.length != 1){
                   System.out.println("Please enter the arguments of the form - java Client/RMIClient2 <Registry IP>");
                   System.exit(1);
                  }

            
        
                if(isIP){
        	  client = new RMIClient2(args[0]);
                 }
               else{
        	 System.out.println("Invalid IP Address supplied...Please enter a valid IP address");
        	 System.exit(1);
                 }
			
             			// Starts worker host thread
			new Thread(client).start();
			
		}

	
}
