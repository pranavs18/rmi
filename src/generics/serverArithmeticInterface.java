/*
 * Id: vsureshk, pranavsa
 * Authors: Vaibhav Suresh Kumar & Pranav Saxena
 */

package generics;


public interface serverArithmeticInterface extends myRemoteInterface{
	public int add(int firstNumber,int secondNumber);
	public int multiply(int firstNumber,int secondNumber);
	public int subtract(int firstNumber, int secondNumber);
	public int divide(int firstNumber, int secondNumber) throws Exception;
}