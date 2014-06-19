package Server;

public class serverArithmetic implements serverArithmeticInterface {
	private int firstNumber;
	private int secondNumber;
	


	//Method to add two numbers
	public int add(int firstNumber, int secondNumber){
		System.out.println("Add method invoked received values = "+firstNumber+" and "+secondNumber);
		int result = firstNumber + secondNumber;
	
		return result;
	}
	
	//Method to multiply two numbers
	public int multiply(int firstNumber, int secondNumber){
		System.out.println("multiply method invoked received values = "+firstNumber+" and "+secondNumber);
		return firstNumber * secondNumber;	
	}
	
	//Method to subtract two numbers
	
	public int subtract(int firstNumber, int secondNumber){
		System.out.println("subtract method invoked received values = "+firstNumber+" and "+secondNumber);
		return firstNumber - secondNumber;
	}
	
	//Method to divide two numbers
	
	public int divide(int firstNumber, int secondNumber){
		System.out.println("divide method invoked received values = "+firstNumber+" and "+secondNumber);
		if(secondNumber != 0){
			return firstNumber/secondNumber;
		}
		
		else{ 
			throw new IllegalArgumentException("Division by zero is not allowed");
			
		}
		
		}
		
	
	
	
}