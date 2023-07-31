package br.com.pedro.springStarter.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.pedro.springStarter.models.entities.Product;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RequestsExceptionsHandller {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Product> threat404(){
		return ResponseEntity.notFound().build();
	}
}
