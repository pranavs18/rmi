/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */


package Server;


import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map.Entry;
import generics.Naming;
import java.util.regex.Pattern;
import registry.AlreadyBoundException;
import registry.NotBoundException;
import registry.RemoteException;
import registry.RemoteObjectRef;

public class remoteServer implements Runnable,Serializable{
	

	private static final long serialVersionUID = 1L;
         
        public static boolean validateIP(String ip){

            final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
            Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
            return IPV4_PATTERN.matcher(ip).matches();
		   
	}

	public static void main(String args[]) throws IOException, NotBoundException{
                 // Starting the RMI Server , requires a string argument - Registry IP 
                
                boolean isIP = validateIP(args[0]);
               if(!isIP){
                 System.out.println("Invalid IP Address supplied...Please enter a valid IP address");
        	 System.exit(1);
               }

               if(args.length != 1){
			System.out.println("Please enter the Arguments of the form - java Server/remoteServer <Registry_IP>");
                        System.exit(1);
						
		}	
		serverArithmetic serAr = new serverArithmetic();
		ConcatenationImpl concat = new ConcatenationImpl();
		FinalCompilerTest finalTest = new FinalCompilerTest();
		MixBagTest mix = new MixBagTest();
		findMaxElement max = new findMaxElement();
		try {
			
		 Naming.bind("//"+args[0]+":1099/test1", serAr);
		 Naming.rebind("//"+args[0]+":1099/test2", concat);
		 Naming.rebind("//"+args[0]+":1099/finaltest", finalTest);
		 Naming.rebind("//"+args[0]+":1099/mix", mix);
		 Naming.rebind("//"+args[0]+":1099/max", max);
		
		} catch (AlreadyBoundException | RemoteException e) {
			e.printStackTrace();
		}

		remoteServer s = new remoteServer();
                System.out.println("RMI Server started on port 9999 and IP Address " + InetAddress.getLocalHost().getHostAddress());
		new Thread(s).start();
		
	}
  
	
	

	@Override
	public void run() {
		
		 ConnectClient clients = new ConnectClient();
		 try {
			clients.createConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
	
