package br.com.pedro.springStarter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pedro.springStarter.exception.RecordNotFoundException;
import br.com.pedro.springStarter.models.entities.Product;
import br.com.pedro.springStarter.models.entities.RequestProduct;
import br.com.pedro.springStarter.models.repositories.ProductRepository;
import jakarta.validation.Valid;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product create(@Valid RequestProduct requestedProduct) {
		Product newProduct = new Product(requestedProduct);
		productRepository.save(newProduct);
		return newProduct;
	}
	
	public List<Product> get() {
		return (List<Product>) productRepository.findAllByAvailableTrue();
	}
	
	public Product get(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
	}
	
	public Iterable<Product> getByPage(int numberPage) {
		Pageable page = PageRequest.of(numberPage, 3);
		return productRepository.findAll(page);
	}
	
	public Product update(@Valid RequestProduct data) {
		
		return productRepository.findById(data.id())
				.map(updatedProduct -> {
					updatedProduct.setName(data.name());
					updatedProduct.setPrice(data.price());
					updatedProduct.setDiscount(data.discount());
					updatedProduct.setAvailable(data.available());
					
					return updatedProduct;
				})
				.orElseThrow(() -> new RecordNotFoundException(data.id()));
	}
	
	/**
	 * soft delete
	 */
	public void delete(Long id) {
		
		productRepository.delete(productRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(id)));
		
	}
}
