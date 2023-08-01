package br.com.pedro.springStarter.models.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.pedro.springStarter.exception.RecordNotFoundException;

/**
 * Controller sugestivo para como Exceções podem ser tratadas
 * 
 * @author Pedro Henrique Pereira de Oliveira
 * @since 1 de ago. de 2023
 */

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(RecordNotFoundException exception) {
		return exception.getMessage();
	}
}
