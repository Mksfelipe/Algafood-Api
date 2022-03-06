package com.algaworks.algafood.api.DTO;

import java.math.BigDecimal;

import com.algaworks.algafood.domain.model.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteDto {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaDto cozinha;
	private Endereco endereco;
	
}
