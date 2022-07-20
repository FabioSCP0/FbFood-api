package com.github.fabioscp0.infrastructure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fabioscp0.domain.exception.EntidadeNaoEncontradaException;
import com.github.fabioscp0.domain.model.FormaPagamento;
import com.github.fabioscp0.domain.repository.FormaPagamentoRepository;
import com.github.fabioscp0.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long id){
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
		if(formaPagamento.isPresent()) return ResponseEntity.ok(formaPagamento.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody FormaPagamento formaPagamento) {
		cadastroFormaPagamentoService.salvar(formaPagamento);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento){
		Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(id);
		if(formaPagamentoAtual.isPresent()) {
			BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");
			FormaPagamento formaPagamentoSalva = cadastroFormaPagamentoService.salvar(formaPagamentoAtual.get());
			return ResponseEntity.ok(formaPagamentoSalva);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			cadastroFormaPagamentoService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
