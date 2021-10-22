package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Permissao;

@Component
public interface PermissaoRepository {

	public List<Permissao> listar();

	public Permissao buscar(Long id);

	public Permissao salvar(Permissao permissao);

	public void remover(Permissao permissao);
}
