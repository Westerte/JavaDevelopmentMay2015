package edu.nesterenko.connectionPool.exception;

public class PhisicalException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5516981466067495582L;
	
	public PhisicalException() {
		super();
	}

	public PhisicalException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PhisicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhisicalException(String message) {
		super(message);
	}

	public PhisicalException(Throwable cause) {
		super(cause);
	}
}
