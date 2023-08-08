package br.com.pedro.springStarter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pedro.springStarter.exception.NullParamException;
import br.com.pedro.springStarter.exception.RecordNotFoundException;
import br.com.pedro.springStarter.mapper.ProductMapper;
import br.com.pedro.springStarter.models.entities.ProductDTO;
import br.com.pedro.springStarter.models.repositories.ProductRepository;
import jakarta.validation.Valid;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductDTO create(@Valid ProductDTO product) {
		return productMapper.toDTO(productRepository.save(productMapper.toEntity(product)));
	}
	
	public List<ProductDTO> get() {
		return productRepository.findAllByAvailableTrue()
				.stream()
				.map(productMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public ProductDTO get(Long id) {
		return productRepository.findById(id)
				.map(p -> productMapper.toDTO(p))
				.orElseThrow(() -> new RecordNotFoundException());
	} 
	
	public List<ProductDTO> getByPage(int numberPage) {
		Pageable page = PageRequest.of(numberPage, 3);
		List<ProductDTO> result = productRepository.findAll(page)
				.stream()
				.map(productMapper::toDTO)
				.collect(Collectors.toList());
		
		return result;
	}
	
	public ProductDTO update(@Valid ProductDTO data) {
		if (data.id() == null) throw new NullParamException("Id");
		
		return productRepository.findById(data.id())
				.map(updatedProduct -> {
					updatedProduct.setName(data.name());
					updatedProduct.setPrice(data.price());
					updatedProduct.setDiscount(data.discount());
					updatedProduct.setAvailable(data.available());
					
					return productMapper.toDTO(updatedProduct);
				})
				.orElseThrow(() -> new RecordNotFoundException());
	}
	
	/**
	 * soft delete
	 */
	public void delete(Long id) {
		productRepository.delete(productRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException()));
		
	}
}
