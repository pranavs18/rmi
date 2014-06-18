package Server;

public class serverArithmetic implements serverArithmeticInterface {
	private int firstNumber;
	private int secondNumber;
	
	public serverArithmetic(int a, int b){
		this.firstNumber = a;
		this.secondNumber = b;
	}
	
	public serverArithmetic() {
		
	}

	//Method to add two numbers
	public int add(int firstNumber, int secondNumber){
		System.out.println("Add method invoked received values = "+firstNumber+" and "+secondNumber);
		return this.firstNumber + this.secondNumber;
	}
	
	//Method to multiply two numbers
	public int multiply(int firstNumber, int secondNumber){
	    return this.firstNumber * this.secondNumber;	
	}
	
	//Method to subtract two numbers
	
	public int subtract(int firstNumber, int secondNumber){
		return this.firstNumber - this.secondNumber;
	}
	
	//Method to divide two numbers
	
	public int divide(int firstNumber, int secondNumber){
		if(this.secondNumber != 0){
			return this.firstNumber/this.secondNumber;
		}
		
		else throw new IllegalArgumentException("Division by zero is not allowed");
	}
	
	
}