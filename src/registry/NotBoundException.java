package registry;

public class NotBoundException extends Exception {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		// constructor which passes a string argument 
		public NotBoundException(String s){
		   super(s);
	   }
	   
	   // constructor without any arguments
	   public NotBoundException(){
		   super();
	   }
}
