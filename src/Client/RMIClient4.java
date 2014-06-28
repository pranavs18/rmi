/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */


package Client;



import generics.Naming;
import generics.findMaxElementInterface;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import registry.RemoteObjectRef;


public class RMIClient4 implements Runnable{

   String ServerIp;
   static int ServerPort = 9999;
   public RMIClient4(String ServerIp){
		
		this.ServerIp = ServerIp;
				
	}
   
   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

	  
	  findMaxElementInterface max = (findMaxElementInterface)Naming.lookUp("rmi://" +ServerIp+":1099/max");
	  ArrayList<String> objectList = new ArrayList<String>();
	  objectList = Naming.listObjects("rmi://"+ServerIp +":1099/"); 
	  System.out.println("LIST OF OBJECTS");
	  
	  for(int i=0;i< objectList.size();i++){
		  System.out.println(objectList.get(i));
	  }
	  
	  ArrayList<Integer> al = new ArrayList<Integer>();
	  System.out.println("Given ArrayList - ");
	  al.add(8);
	  al.add(6);
	  al.add(3);
	  al.add(31);
	  al.add(-3);
	  for(int i=0;i<al.size();i++){
		  System.out.println("Number" + (i+1) + " " + al.get(i));
	  }
	  
	  int maxElement = max.findMax(al);
	  System.out.println("The maximum element in the list is " + maxElement);

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
		
		
                 boolean ip = validateIP(args[0]);
                RMIClient4 client = null;
                if(args.length != 1){
                System.out.println("Please enter the arguments of the form - java Client/RMIClient4 <Registry IP>");
                System.exit(1);
            }

               if(ip){
                  client = new RMIClient4(args[0]);
               }

               else{
                      System.out.println("Invalid IP Address supplied...Please enter a valid IP address");
                      System.exit(1);
                   }
	
		// Starts worker host thread
		new Thread(client).start();
		
	}


}
