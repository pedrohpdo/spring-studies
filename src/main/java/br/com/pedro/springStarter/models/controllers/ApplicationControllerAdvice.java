package br.com.pedro.springStarter.models.controllers;

import org.springframework.dao.DataIntegrityViolationException;
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
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception e, HttpServletRequest request) {
		
		StandartError result = new StandartError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unknown Problem Ocurred",
				e.getMessage(),
				request.getRequestURI());
		
		logger.error("Unknown Problem Ocurred", e);
		
		return ResponseEntity.internalServerError().body(result);
		
	}

	/**
	 * 
	 * Intercepta uma exceção devido a não encontrar uma entidade no sistemas
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
		
		logger.error("Cannot Find Entity", exception);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}
	

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataViolatedExcepetion(DataIntegrityViolationException e,
			HttpServletRequest request) {
		
		StandartError result = new StandartError(HttpStatus.CONFLICT.value(),
				"Conflict Data",
				e.getMostSpecificCause().getMessage(),
				request.getRequestURI());

		logger.error("Dados conflituosos dentro do sistema", e);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(result);

	}
}
