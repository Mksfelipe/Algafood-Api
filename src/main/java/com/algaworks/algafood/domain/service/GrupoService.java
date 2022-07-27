package com.algaworks.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class GrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";
	private static final String NOME_JA_EM_USO = "Grupo com o nome: %s, ja existe";

	@Autowired
	private GrupoRepository grupoRespository;

	@Autowired
	private PermissaoService permissaoService;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		try {
			return grupoRespository.save(grupo);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(NOME_JA_EM_USO, grupo.getNome()));
		}
	}

	@Transactional
	public void excluir(Long grupoId) {
		try {

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (EntidadeEmUsoException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}

	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRespository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

		grupo.removerPermissao(permissao);
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

		grupo.adicionarPermissao(permissao);
	}

}
