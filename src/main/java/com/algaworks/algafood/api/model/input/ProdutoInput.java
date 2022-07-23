package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {
	
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private String descricao;
	
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer=3, fraction=2)
	private BigDecimal preco;
	
	private Boolean ativo = true;
	
}
