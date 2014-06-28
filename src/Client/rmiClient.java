/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */

package Client;

import generics.Naming;
import generics.serverArithmeticInterface;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.UnknownHostException;
public class rmiClient implements Runnable{

   String serverIp;
   static int ServerPort = 9999;
   public rmiClient(String serverIp){
           this.serverIp = serverIp;		
				
	}
   
   public void startrmiClient(String ServerIp) throws UnknownHostException, IOException, InterruptedException{

	   serverArithmeticInterface remoteObject = (serverArithmeticInterface)Naming.lookUp("rmi://"+ serverIp + ":1099/test1");
	   int retVal = 0;
	try {
		retVal = remoteObject.divide(20, 4);
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
			startrmiClient(serverIp);
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
		
            boolean ip = validateIP(args[0]);
            rmiClient client = null;
	        if(args.length != 1){
                System.out.println("Please enter the arguments of the form - java Client/rmiClient <Registry IP>");
                System.exit(1);
            }

               if(ip){
        	  client = new rmiClient(args[0]);
	       }
		
	       else{
		      System.out.println("Invalid IP Address supplied...Please enter a valid IP address");
		      System.exit(1);
		   }
                //Starts worker host thread
		new Thread(client).start();
		
	}
	
}
