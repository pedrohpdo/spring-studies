package br.com.pedro.springStarter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedro.springStarter.exception.ObjectNotFoundException;
import br.com.pedro.springStarter.models.entities.Product;
import br.com.pedro.springStarter.models.entities.RequestProduct;
import br.com.pedro.springStarter.models.repositories.ProductRepository;
import jakarta.validation.Valid;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Iterable<Product> getProducts() {
		return productRepository.findAllByAvailableTrue();
	}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public Optional<Product> updateProduct(@Valid RequestProduct data) {
		
		return productRepository.findById(data.id())
				.map(updatedProduct -> {
					updatedProduct.setName(data.name());
					updatedProduct.setPrice(data.price());
					updatedProduct.setDiscount(data.discount());
					updatedProduct.setAvailable(data.available());
					
					return updatedProduct;
				});
	}
	
	/**
	 * soft delete
	 */
	public boolean deleteProductById(Long id) {
		
		return productRepository.findById(id)
				.map(recordFound -> {
					recordFound.setAvailable(false);
					return true;
				}).orElse(false);
	}
}
