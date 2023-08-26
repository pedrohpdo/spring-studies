package br.com.pedro.springStarter.infra.exception;

public class NullParamException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullParamException(String value) {
		super("Param must not be null | Param : " + value);
	}
	
}
