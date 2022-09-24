package com.algaworks.algafood.domain.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class VendaDiaria {

	private String data;
	private Long totalVendas;
	private BigDecimal totalFatura;
	
}
