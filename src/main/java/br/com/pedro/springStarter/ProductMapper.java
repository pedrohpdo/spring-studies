package br.com.pedro.springStarter;

import org.springframework.stereotype.Component;

import br.com.pedro.springStarter.models.entities.Product;
import br.com.pedro.springStarter.models.entities.ProductDTO;

@Component
public class ProductMapper {
	
	public ProductDTO toDTO(Product product) {
		if (product == null) return null;
	
		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getPrice(),
				product.getDiscount(),
				product.isAvailable()
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
