package br.com.pedro.springStarter.models.entities;

import org.hibernate.annotations.SQLDelete;

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
 * Represetação de um Produto genérico utilizado dentro dos modelos do exercicio.
 * 
 * @author Pedro Henrique Pereira de Oliveira
 *
 */

@Entity
@Table(name = "products_table")
@SQLDelete(sql = "UPDATE products_table set available = false WHERE id = ?")
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

	private boolean available;

	public Product() {
	}

	public Product(String name, double price, double discount) {
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.available = true;
	}

	public Long getId() {
		return id;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	
	
}
