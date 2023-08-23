package br.com.pedro.springStarter.infra.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorResponse {

	private Instant instant; // momento que a exception foi lancada
	private Integer status; // codigo da resposta da requisicao
	private String message;
	//private String StackTrace; // mapeamento/caminho do erro

	/**
	 * uma lista que armazena objetos do tipo ValidationError. Cada objeto
	 * ValidationError representa um erro de validação específico associado a um
	 * determinado campo e possui dois atributos: field (campo) e message
	 * (mensagem).
	 */
	private List<ValidationError> errors;
	
	/**
	 * Classe que vai determinar os erros de validação do usuário 
	 * 
	 * @author Pedro Henrique Pereira de Oliveira
	 * @since 4 de ago. de 2023
	 */
	private static class ValidationError {
		private final String FIELD;
		private final String ERROR;

		public ValidationError(String FIELD, String ERROR) {
			this.FIELD = FIELD;
			this.ERROR = ERROR;
		}

		public String getFIELD() {
			return FIELD;
		}

		public String getERROR() {
			return ERROR;
		}
	}

	/**
	 * cria um nova resposta a requisicao
	 * 
	 * @param status
	 * @param message
	 *
	 */
	public ErrorResponse(Integer status, String message) {
		this.instant = Instant.now();
		this.status = status;
		this.message = message;
	}

	public void addValidationError(String field, String error) {
		if (Objects.isNull(errors)) {
			this.errors = new ArrayList<ValidationError>();
		}
		this.errors.add(new ValidationError(field, error));
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getStackTrace() {
//		return StackTrace;
//	}
//
//	public void setStackTrace(String stackTrace) {
//		StackTrace = stackTrace;
//	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}
	
	

}
