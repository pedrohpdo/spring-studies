package br.com.pedro.springStarter.models.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequestMapping("/api/produtos")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping
	public @ResponseBody Product newProduct(@Valid Product newProduct) {
		productRepository.save(newProduct);
		return newProduct;
	}
	
	@GetMapping
	public @ResponseBody Iterable<Product> getProducts(){
		return productRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	public @ResponseBody Optional<Product> getProductById(@PathVariable Long id) {
		return productRepository.findById(id);
	}
	
	@GetMapping(path="/page/{numberPage}")
	public @ResponseBody Iterable<Product> getPages(@PathVariable int numberPage){
		Pageable page = PageRequest.of(numberPage, 3);
		
		return productRepository.findAll(page);
	}
	
	@PutMapping
	public Product updateProduct(@Valid Product produto) {
		return productRepository.save(produto);
	}
	
	@DeleteMapping(path="/{id}")
	public String deleteProductById(@PathVariable Long id) {
		productRepository.deleteById(id);
		return "sucess";
	}
	
	
}
