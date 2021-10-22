package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;

@Component
public interface RestauranteRepository {

	public List<Restaurante> listar();

	public Restaurante buscar(Long id);

	public Restaurante salvar(Restaurante restaurante);

	public void remover(Long id);

}
