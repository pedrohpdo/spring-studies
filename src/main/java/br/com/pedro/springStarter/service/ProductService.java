package br.com.pedro.springStarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.pedro.springStarter.models.entities.Product;
import br.com.pedro.springStarter.models.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 
	 * @return
	 */

	@GetMapping
	public @ResponseBody ResponseEntity<Iterable<Product>> getProducts() {
		return ResponseEntity.ok(productRepository.findAllByAvailableTrue());
	}
}
