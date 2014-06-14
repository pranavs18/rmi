package registry;

// defining our own exception handling mechanisms for our RMI framework
// need to call the super classes 
public class AlreadyBoundException extends Exception {
    
	private static final long serialVersionUID = 1L;

	// constructor which passes a string argument 
	public AlreadyBoundException(String s){
	   super(s);
   }
   
   // constructor without any arguments
   public AlreadyBoundException(){
	   super();
   }
}
