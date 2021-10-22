package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado buscar(Long id) {
		return estadoRepository.buscar(id);
	}

	public List<Estado> listar() {
		return estadoRepository.listar();
	}

	public Estado atualizar(Long estadoId, Estado estado) {
		Estado estadoAtual = estadoRepository.buscar(estadoId);

		if (estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");

			estadoAtual = estadoRepository.salvar(estadoAtual);
			return estadoAtual;
		}

		return estado;
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}

	public void excluir(Long id) {

		try {
			estadoRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de estado com código %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso", id));
		}

	}

}
