package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de codigo %d nao pode ser removida, pois esta em uso";

	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Nao existe um cadastro de Cozinha com codigo %d";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public Page<Cozinha> listar(Pageable pageable) {
		Page<Cozinha> cozinhaPage = cozinhaRepository.findAll(pageable);
	
		return cozinhaPage;
	}

	public Cozinha atualizar(Long id, Cozinha cozinha) {
		Cozinha cozinhaAtual = buscarOuFalhar(id);

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		return salvar(cozinhaAtual);

	}

	@Transactional
	public void remover(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		}
	}

	public List<Cozinha> listarPorNome(String nome) {
		return cozinhaRepository.findByNomeIgnoreCaseContaining(nome);
	}

	public Cozinha buscarOuFalhar(Long id) {
		return cozinhaRepository.findById(id)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, id)));
	}
}
