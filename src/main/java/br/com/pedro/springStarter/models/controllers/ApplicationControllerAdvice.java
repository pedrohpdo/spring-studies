package br.com.pedro.springStarter.models.controllers;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pedro.springStarter.exception.ErrorResponse;
import br.com.pedro.springStarter.exception.RecordNotFoundException;
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

		ErrorResponse result = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Unknown Problem Ocurred");

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

		ErrorResponse result = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(),
				exception.getMessage());

		logger.error("Cannot Find Entity", exception);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}

	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> integrityConstrainException(
			DataIntegrityViolationException exception,
			WebRequest webRequest) {
		
		ErrorResponse response = new ErrorResponse(
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				exception.getMostSpecificCause().getMessage()
				);
		
		
		logger.error("Cannot Validate Data", exception);
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(
			DataIntegrityViolationException exception,
			WebRequest webRequest) {
		
		ErrorResponse response = new ErrorResponse(
				HttpStatus.CONFLICT.value(),
				exception.getMostSpecificCause().getMessage()
				);
		
		
		logger.error("Dados conflituosos dentro do sistema", exception);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest webRequest) {
		
		ErrorResponse response = new ErrorResponse(
				HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Invalid Arguments");
		
		logger.error("Invalid Data Params");
		
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			response.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
	}
}
