package edu.nesterenko.airline.exception;

public class PhisicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7744353106156509699L;

	public PhisicalException() {
		super();
	}

	public PhisicalException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PhisicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PhisicalException(String arg0) {
		super(arg0);
	}

	public PhisicalException(Throwable arg0) {
		super(arg0);
	}

}
