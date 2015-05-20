package edu.vadimnesterenko.airline.entity;

public class BadParamException extends Exception {

	public BadParamException() {
		super();
	}

	public BadParamException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BadParamException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BadParamException(String arg0) {
		super(arg0);
	}

	public BadParamException(Throwable arg0) {
		super(arg0);
	}

}
