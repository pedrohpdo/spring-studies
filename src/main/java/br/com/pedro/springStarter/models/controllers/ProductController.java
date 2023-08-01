package br.com.pedro.springStarter.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.springStarter.models.entities.Product;
import br.com.pedro.springStarter.models.entities.RequestProduct;
import br.com.pedro.springStarter.models.repositories.ProductRepository;
import br.com.pedro.springStarter.service.ProductService;
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
	ProductService productService;

	@Autowired
	ProductRepository productRepository;
		
	@PostMapping
	public @ResponseBody ResponseEntity<Product> addProduct(@RequestBody @Valid RequestProduct requestedProduct) {
		Product newProduct = new Product(requestedProduct);
		productRepository.save(newProduct);
		return ResponseEntity.ok().build();
	}
		
	@GetMapping
	public @ResponseBody ResponseEntity<Iterable<Product>> getProducts() {
		return ResponseEntity.ok(productService.get());
	}
	
	@GetMapping(path = "/{id}")
	public @ResponseBody Product getProductById(@PathVariable Long id) {
		
		return productService.get(id);
		
	}
		
	@GetMapping(path = "/page/{numberPage}")
	public @ResponseBody ResponseEntity<Iterable<Product>> getByPage(@PathVariable int numberPage) {
		
		return ResponseEntity.ok(productService.getByPage(numberPage));
	}
	
	
	@PutMapping
	@Transactional
	public ResponseEntity<Product> update(@RequestBody @Valid RequestProduct data) {
		
		return productService.update(data)
				.map(updatedProduct -> ResponseEntity.ok(updatedProduct))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Product> delete(@PathVariable Long id) {
		if (productService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
