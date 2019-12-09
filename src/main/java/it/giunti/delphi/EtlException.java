package it.giunti.delphi;

public class EtlException extends Exception {
	private static final long serialVersionUID = -6551010770835665190L;

	private Throwable error;
	private String message;
	
	public EtlException(String message, Throwable error) {
		this.error = error;
		this.message = message;
	}

	public Throwable getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}
	
	
}
