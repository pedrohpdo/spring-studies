package br.com.pedro.springStarter.models.controllers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.pedro.springStarter.exception.RecordNotFoundException;
import br.com.pedro.springStarter.exception.StandartError;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller sugestivo para como Exceções podem ser tratadas
 * 
 * @author Pedro Henrique Pereira de Oliveira
 * @since 1 de ago. de 2023
 */

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<StandartError> handleNotFoundException(RecordNotFoundException exception, HttpServletRequest e) {
		StandartError result = new StandartError();
		
		result.setInstant(Instant.now());
		result.setStatus(HttpStatus.NOT_FOUND.value());
		result.setError("Record Not Found");
		result.setMessage(exception.getMessage());
		result.setPath(e.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
	}
}
