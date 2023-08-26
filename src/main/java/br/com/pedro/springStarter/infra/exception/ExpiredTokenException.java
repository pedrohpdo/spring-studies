package br.com.pedro.springStarter.infra.exception;

public class ExpiredTokenException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ExpiredTokenException(String message) {
		super("Invalid Token. Cause: " + message);
	}
 }
