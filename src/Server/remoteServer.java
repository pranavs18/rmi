package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;



import generics.Naming;
import registry.AlreadyBoundException;
import registry.RemoteException;
import registry.RemoteObjectRef;

public class remoteServer implements Runnable,Serializable{
	

	private static final long serialVersionUID = 1L;
	private static String Ipaddress = null;
    private static int port ;
    private int conn=0;
    private Socket connection;
    private int ID;
    
   
	public remoteServer(Socket SOCK, int i) {
		this.connection = SOCK;
		this.ID = i;
	}

	public remoteServer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws IOException{
		
		regServerTest test = new regServerTest();
		
		try {
			Naming.bind("//127.0.0.1:1099/firstObject", test);
			Naming.rebind("//127.0.0.1:1099/firstObject", test);
		} catch (AlreadyBoundException | RemoteException e) {
			
			e.printStackTrace();
		}
		Ipaddress = InetAddress.getLocalHost().getHostAddress();
		port = 9999;
		remoteServer s = new remoteServer();
		new Thread(s).start();
		
	}
  
	
	public void createConnection() throws IOException{
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			System.out.println("Server started on IP Address "+ Ipaddress + " and port " + port);
		while ( true ) {
			
		    try {
			Socket clientSocket = ss.accept();
			conn++;
			clientConnection newconn = new clientConnection(clientSocket, conn, this);
			new Thread(newconn).start();
		    }   
		    catch (IOException e) {
			System.out.println(e);
		    }
	
		}
	}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		finally{
			ss.close();
		}
	}

	@Override
	public void run() {
		
		 remoteServer rms = new remoteServer();
		 try {
			rms.createConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class clientConnection implements Runnable {
	  Socket SOCK;
	  remoteServer server;
	  int id;
	  BufferedReader br = null;
    PrintStream ps = null;
    boolean done = false;
  
    public clientConnection(Socket client, int id, remoteServer pm) {
			this.SOCK = client;
			this.id = id;
			this.server = pm;
			System.out.println( "Connection " + id + " established with: " + SOCK );
			
			try {
			    br = new BufferedReader(new InputStreamReader(SOCK.getInputStream()));
			    ps = new PrintStream(SOCK.getOutputStream());
			} catch (IOException e) {
			    System.out.println(e);
			}
		}
    
	public void run(){
		while(!done){
			try {			
				String message = br.readLine();			
			} catch (Exception e) {
				
				System.out.println( "Connection " + id + " closed." );
				
				 try {
					br.close();
					ps.close();
		            SOCK.close();
				} catch (IOException e1) {
					
				}
	             
				break;
			}
		 
		
		}
	}
}
	
