package br.com.pedro.springStarter.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedro.springStarter.models.entities.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAllByAvailableTrue();
	
}
