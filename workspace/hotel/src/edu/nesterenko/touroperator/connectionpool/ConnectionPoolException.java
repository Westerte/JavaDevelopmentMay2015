package edu.nesterenko.touroperator.connectionpool;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 8807750987579857750L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

}
