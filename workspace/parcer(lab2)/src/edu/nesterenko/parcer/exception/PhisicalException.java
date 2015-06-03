package edu.nesterenko.parcer.exception;

public class PhisicalException extends Exception {

	private static final long serialVersionUID = -7744353106156509699L;

	public PhisicalException() {
		super();
	}

	public PhisicalException(String message, Throwable error) {
		super(message, error);
	}

	public PhisicalException(String message) {
		super(message);
	}

	public PhisicalException(Throwable error) {
		super(error);
	}

}
