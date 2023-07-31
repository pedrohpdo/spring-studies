package br.com.pedro.springStarter.models.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.pedro.springStarter.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(ObjectNotFoundException e) {
		return e.getMessage();
	}
}
