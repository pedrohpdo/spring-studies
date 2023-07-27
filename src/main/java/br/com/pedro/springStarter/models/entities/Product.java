package br.com.pedro.springStarter.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 * Represetação de um Produto genérico qualquer utilizado dentro dos modelos do
 * exercicio
 * 
 * @author Pedro Henrique Pereira de Oliveira
 *
 */

@Entity
@Table(name = "products_table")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false, length = 30)
	private String name;

	@Min(value = 0)
	private double price;

	@Min(value = 0)
	@Max(value = 1)
	private double discount;
	
	private boolean active;
	
	public Product() {
	}

	public Product(String name, double price, double discount) {
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.active = true;
	}

	public Product(RequestProduct requestProd) {
		this.id = requestProd.id();
		this.name = requestProd.name();
		this.price = requestProd.price();
		this.discount = requestProd.discount();
		this.active = requestProd.isActive();
	}

	public Long getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
}	
