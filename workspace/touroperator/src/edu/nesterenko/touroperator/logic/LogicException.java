package edu.nesterenko.touroperator.logic;

public class LogicException extends Exception {

	private static final long serialVersionUID = 2617970064808510548L;

	public LogicException() {
		super();
	}
	
	public LogicException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LogicException(String message) {
		super(message);
	}
	
	public LogicException(Throwable cause) {
		super(cause);
	}

}
