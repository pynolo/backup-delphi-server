package it.giunti.delphi;

import java.io.IOException;

public class ControllerException extends IOException {
	private static final long serialVersionUID = -5088542861308609230L;
	private Throwable error;
	private String message;
	
	public ControllerException(String message, Throwable error) {
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
