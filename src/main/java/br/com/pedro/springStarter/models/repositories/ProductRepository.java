package br.com.pedro.springStarter.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.pedro.springStarter.models.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}
