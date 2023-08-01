package br.com.pedro.springStarter.exception;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(Long id) {
		super("Registro não encontrado [Id: " + id + "]"); 
	}
	
}
