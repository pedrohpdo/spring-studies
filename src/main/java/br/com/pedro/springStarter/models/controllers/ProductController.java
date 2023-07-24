package br.com.pedro.springStarter.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.springStarter.models.entities.Product;
import br.com.pedro.springStarter.models.repositories.ProductRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/novoProduto")
	public Product newProduct(Product newProduct) {
		productRepository.save(newProduct);
		return newProduct;
	}

}
