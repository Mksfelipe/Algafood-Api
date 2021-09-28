package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	public List<FormaPagamento> listar();

	public FormaPagamento buscar(Long id);

	public FormaPagamento salvar(FormaPagamento formaPagamento);

	public void remover(FormaPagamento formaPagamento);
}
