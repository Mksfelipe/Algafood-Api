package com.algaworks.algafood.domain.repository;

import java.util.List;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.domain.model.Cozinha;

@Component
public interface CozinhaRepository {

	public List<Cozinha> listar();
	public List<Cozinha> consultarPorNome(String nome);
	

	public Cozinha buscar(Long id);

	public Cozinha salvar(Cozinha cozinha);

	public void remover(Long id);

}
