package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Permissao;

public interface PermissaoRepository {

	public List<Permissao> listar();

	public Permissao buscar(Long id);

	public Permissao salvar(Permissao permissao);

	public void remover(Permissao permissao);
}
