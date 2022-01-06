package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	private LocalDate dataCriacao;
	private LocalDate dataConfirmacao;
	private LocalDate dataCancelamento;
	private LocalDate dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	private StatusPedido status;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
}