package br.com.pedro.springStarter.mapper;

import org.springframework.stereotype.Component;

import br.com.pedro.springStarter.models.entities.product.Product;
import br.com.pedro.springStarter.models.entities.product.ProductDTO;

@Component
public class ProductMapper {
	
	public ProductDTO toDTO(Product product) {
		if (product == null) return null;

		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getDiscount(),
				true
				);
	}
	
	public Product toEntity(ProductDTO dto) {
		if (dto == null) return null;
		
		Product product = new Product();
		
		product.setId(dto.id());
		product.setName(dto.name());
		product.setPrice(dto.price());
		product.setDiscount(dto.discount());
		product.setAvailable(true);
		
		return product;
	
	}

}
