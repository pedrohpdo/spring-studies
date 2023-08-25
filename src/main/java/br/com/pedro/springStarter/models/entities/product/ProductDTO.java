package br.com.pedro.springStarter.models.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Descrição de um produto que será passado pelo corpo da requisção HTTP.
 * 
 * @author Pedro Henrique Pereira de Oliveira
 * @since 27 de jul. de 2023
 */

public record ProductDTO(Long id,

		@NotBlank String name,

		@Min(0) double price,

		@Min(0) @Max(1) double discount,

		boolean available
) {
}
