package br.com.pedro.springStarter.exception;

/**
 * Custom Exception
 * @author Pedro Henrique Pereira de Oliveira
 * @since 1 de ago. de 2023
 */

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(Long id) {
		super("Registro não encontrado [Id: " + id + "]"); 
	}
	
}
