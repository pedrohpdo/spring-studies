package br.com.pedro.springStarter.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(Long id) {
		super("Registro não encontrado. Id: " + id); 
	}
	
}
