package br.com.pedro.springStarter.models.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.springStarter.models.entities.Product;
import br.com.pedro.springStarter.models.repositories.ProductRepository;
import jakarta.validation.Valid;

/**
 * Controller responsável por determinar as funções da api
 * 
 * @author Pedro Henrique Pereira de Oliveira
 *
 */

@RestController
@RequestMapping("/api/produtos")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	@PostMapping
	public ResponseEntity<Product> newProduct(@Valid Product newProduct) {
		productRepository.save(newProduct);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Iterable<Product>> getProducts(){
		
		return ResponseEntity.ok(productRepository.findAll());
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Optional<Product> prod = productRepository.findById(id);

		return prod.isPresent() ? ResponseEntity.ok(prod.get()) : ResponseEntity.noContent().build();
	}
	
	@GetMapping(path="/page/{numberPage}") 	
	public @ResponseBody ResponseEntity<Iterable<Product>> getPages(@PathVariable int numberPage){
		Pageable page = PageRequest.of(numberPage, 3);
		
		return ResponseEntity.ok(productRepository.findAll(page));
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@Valid Product produto) {
		productRepository.save(produto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
