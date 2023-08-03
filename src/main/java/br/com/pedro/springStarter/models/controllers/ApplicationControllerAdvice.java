package br.com.pedro.springStarter.models.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pedro.springStarter.exception.RecordNotFoundException;
import br.com.pedro.springStarter.exception.StandartError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller sugestivo para como Exceções podem ser tratadas
 * 
 * @author Pedro Henrique Pereira de Oliveira
 * @since 1 de ago. de 2023
 */

@Slf4j(topic = "APPLICATION_EXCEPTION_HANDLER") // adiciona a anotação no console
@RestControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * Intercepta uma exceção lançada pela camada Service e a trava devidamente
	 * 
	 * @param exception RecordNotFoundException
	 * @param request
	 * @return status 404 + StandartError response
	 */

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(RecordNotFoundException exception,
			HttpServletRequest request) {
		StandartError result = new StandartError(HttpStatus.NOT_FOUND.value(),
												"Record not Found",
												exception.getMessage(),
												request.getRequestURI());

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
	}
	
	
}
