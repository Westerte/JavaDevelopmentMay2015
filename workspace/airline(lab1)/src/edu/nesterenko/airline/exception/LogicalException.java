package edu.nesterenko.airline.exception;

public class LogicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6805030032753229982L;

	public LogicalException() {
		super();
	}

	public LogicalException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public LogicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LogicalException(String arg0) {
		super(arg0);
	}

	public LogicalException(Throwable arg0) {
		super(arg0);
	}

}
