package exceptions;

public class NumberArgumentsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NumberArgumentsException(int a, int b) {
		System.out.println("The number of arguments introduced is incorrect. It must be "+a+" and you have introduced "+b);
	}
}
