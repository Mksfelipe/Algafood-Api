package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

@Component
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	// public List<Cozinha> consultarPorNome(String nome);

}
