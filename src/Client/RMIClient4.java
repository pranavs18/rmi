package Client;



import generics.Naming;
import generics.findMaxElementInterface;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map.Entry;

import registry.RemoteObjectRef;


public class RMIClient4 implements Runnable{

   String ServerIp;
   static int ServerPort = 9999;
   public RMIClient4(String ServerIp){
		
		this.ServerIp = ServerIp;
				
	}
   
   public void startrmiClient(String ServerIp, int ServerPort) throws UnknownHostException, IOException, InterruptedException{

	  
	  findMaxElementInterface max = (findMaxElementInterface)Naming.lookUp("//128.237.190.47:1099/max");
	  ArrayList<String> objectList = new ArrayList<String>();
	  objectList = Naming.listObjects("//128.237.190.47:1099/"); 
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
	
public static void main(String[] args){
		
		
		String ServerIp = args[0];		
	
		RMIClient4 client = new RMIClient4(ServerIp);
		
		// Starts worker host thread
		new Thread(client).start();
		
	}


}
