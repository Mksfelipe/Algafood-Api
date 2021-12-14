package com.algaworks.algafood.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Optional<Restaurante> buscar(Long id) {
		return restauranteRepository.findById(id);
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

		if (cozinha.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		}

		restaurante.setCozinha(cozinha.get());

		return restauranteRepository.save(restaurante);
	}

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public Restaurante atualizar(Long id, Restaurante restaurante) {
		 Restaurante restauranteAtual = restauranteRepository
				.findById(id).orElse(null);
		
		if (restauranteAtual != null) {
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro");
			
			restauranteAtual = restauranteRepository.save(restauranteAtual);
			return restauranteAtual;
		}
		return restaurante;

	}

	public Restaurante atualizarParcial(Long id, Map<String, Object> campos) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

		if (restauranteAtual.isEmpty()) {
			return restauranteAtual.get();
		}

		merge(campos, restauranteAtual.get());

		return atualizar(id, restauranteAtual.get());

	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem,  Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
			
		});
	}
	
	
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com código %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
		}
	}

}
