package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProdutoInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	private Integer restauranteId;

	@Valid
	@NotNull
	private String descricao;
	
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal preco;
	
	@NotNull
	private char ativo = 'Y';
}
