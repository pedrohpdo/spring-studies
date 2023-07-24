package br.com.pedro.springStarter.models.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.springStarter.models.entities.Product;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {
	
	@PostMapping("/novoProduto")
	public Product newProduct(@RequestParam String name) {
		return new Product(name);
	}

}
