package br.com.pedro.springStarter.models.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		return ResponseEntity.ok(productService.getProducts());
	}
	
	@GetMapping(path = "/{id}")
	public @ResponseBody Product getProductById(@PathVariable Long id) {
		
		return productService.getProductById(id);
		
	}
		
	@GetMapping(path = "/page/{numberPage}")
	public @ResponseBody ResponseEntity<Iterable<Product>> getPages(@PathVariable int numberPage) {
		Pageable page = PageRequest.of(numberPage, 3);

		return ResponseEntity.ok(productRepository.findAll(page));
	}
	
	
	@PutMapping
	@Transactional
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid RequestProduct data) {
		
		return productService.updateProduct(data)
				.map(updatedProduct -> ResponseEntity.ok(updatedProduct))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Product> deleteProductById(@PathVariable Long id) {
		Optional<Product> dataProduct = productRepository.findById(id);

		if (dataProduct.isPresent()) {
			Product deletedProduct = dataProduct.get();
			deletedProduct.setAvailable(false);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
