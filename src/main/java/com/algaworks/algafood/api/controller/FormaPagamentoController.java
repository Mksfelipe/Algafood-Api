package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private FormaPagamentoService formaPagamentoService;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

	@GetMapping
	public List<FormaPagamentoModel> listar() {
		return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
	}

	@GetMapping("/{id}")
	public FormaPagamentoModel buscar(@PathVariable Long id) {
		return formaPagamentoModelAssembler.toModel(formaPagamentoService.buscarOuFalhar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

		formaPagamento = formaPagamentoService.salvar(formaPagamento);

		return formaPagamentoModelAssembler.toModel(formaPagamento);
	}

	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

		formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

		formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

		return formaPagamentoModelAssembler.toModel(formaPagamentoAtual);
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);
	}
}