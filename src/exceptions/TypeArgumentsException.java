package exceptions;

public class TypeArgumentsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeArgumentsException(String string) {
		System.out.println("The type of the arguments is incorrect. Try:"+string);
	}
}
