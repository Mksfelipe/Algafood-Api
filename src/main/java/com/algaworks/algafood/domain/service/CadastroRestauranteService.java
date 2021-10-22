package com.algaworks.algafood.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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

	public Restaurante buscar(Long id) {
		return restauranteRepository.buscar(id);
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		}

		restaurante.setCozinha(cozinha);

		return restauranteRepository.salvar(restaurante);
	}

	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	public Restaurante atualizar(Restaurante restaurante, Long id) {
		Restaurante restauranteAtual = restauranteRepository.buscar(id);

		if (restauranteAtual != null) {
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
			restauranteAtual = restauranteRepository.salvar(restauranteAtual);
			return restauranteAtual;
		}

		return restaurante;

	}

	public Restaurante atualizarParcial(Long id, Map<String, Object> campos) {
		Restaurante restauranteAtual = restauranteRepository.buscar(id);

		if (restauranteAtual == null) {
			return restauranteAtual;
		}

		merge(campos, restauranteAtual);

		return atualizar(restauranteAtual, id);

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

}
