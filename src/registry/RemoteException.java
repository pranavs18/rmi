/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */


package registry;


public class RemoteException extends Exception {
   
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    // constructor to display the cause of the remote exception without any arguments
	public RemoteException(){
	        super();
	    }

	//constructor with argument as a string message     
	    public RemoteException(String message){
	        super(message);
	        
	    }

}
