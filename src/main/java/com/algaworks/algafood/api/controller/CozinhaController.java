package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@GetMapping
	public List<Cozinha> listar() {
		return cadastroCozinhaService.listar();
	}

	@GetMapping("/{id}")
	public Cozinha buscar(@PathVariable Long id) {
		return cadastroCozinhaService.buscarOuFalhar(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}

	@PutMapping("/{id}")
	public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

		cozinha = cadastroCozinhaService.atualizar(id, cozinha);

		return cozinha;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCozinhaService.remover(id);

	}

	@GetMapping("/por-nome")
	public List<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome) {

		return cadastroCozinhaService.listarPorNome(nome);
	}

}
