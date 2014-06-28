package org.twbbs.pccprogram.scratchpp;

public class CompileException extends RuntimeException {

	private static final long serialVersionUID = -6914638985345070220L;

	public CompileException() {
	}

	public CompileException(String message) {
		super(message);
	}

	public CompileException(Throwable cause) {
		super(cause);
	}

	public CompileException(String message, Throwable cause) {
		super(message, cause);
	}
}
