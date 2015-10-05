package edu.nesterenko.touroperator.validation;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 7451858452577579154L;

	public ValidationException() {
		super();
	}
	
	public ValidationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(Throwable cause) {
		super(cause);
	}

}
