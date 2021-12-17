package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Endereco {

	private final String endereco = "endereco_";

	@Column(name = endereco + "cep")
	private String cep;

	@Column(name = endereco + "logradouro")
	private String logradouro;

	@Column(name = endereco + "numero")
	private String numero;

	@Column(name = endereco + "complemento")
	private String complemento;

	@Column(name = endereco + "bairro")
	private String bairro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = endereco + "cidade_id")
	private Cidade cidade;

}
