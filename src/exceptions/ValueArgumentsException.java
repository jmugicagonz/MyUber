package exceptions;

public class ValueArgumentsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValueArgumentsException(String string) {
		System.out.println("The value of at least one argument is incorrect: "+string);
	}

}
