package br.com.pedro.springStarter.models.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RequestProduct(
		Long id,
		
		@NotBlank
		String name,
		
		@Min(0)
		double price,
		
		@Min(0)
		@Max(1)
		double discount,
		
		boolean available
		
		) {}
