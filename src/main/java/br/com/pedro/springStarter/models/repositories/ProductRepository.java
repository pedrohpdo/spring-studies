package br.com.pedro.springStarter.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.pedro.springStarter.models.entities.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long>, JpaRepository<Product, Long>{
	
	Iterable<Product> findAllByAvailableTrue();
	
}
