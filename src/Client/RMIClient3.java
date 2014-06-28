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
import java.util.regex.Pattern;
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

		  
		  MixbagTestInterface mix = (MixbagTestInterface)Naming.lookUp("rmi://"+ ServerIp + ":1099/mix");
		  
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

                public static boolean validateIP(String ip){
                   // IP VALIDATION
		   final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
		   Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
           return IPV4_PATTERN.matcher(ip).matches();
		   
		}

		
	public static void main(String[] args){
			
			
                boolean isIP = validateIP(args[0]);
        	RMIClient3 client = null;
	        if(args.length != 1){
                System.out.println("Please enter the arguments of the form - java Client/RMIClient3 <Registry IP>");
                System.exit(1);
            }

            
        
                if(isIP){
        	  client = new RMIClient3(args[0]);
               }
                else{
        	 System.out.println("Invalid IP Address supplied...Please enter a valid IP address");
        	 System.exit(1);
                }
		
			
			// Starts worker host thread
			new Thread(client).start();
			
		}

	
}
