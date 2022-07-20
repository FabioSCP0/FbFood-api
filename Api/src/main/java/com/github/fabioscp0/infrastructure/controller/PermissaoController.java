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
import com.github.fabioscp0.domain.model.Permissao;
import com.github.fabioscp0.domain.repository.PermissaoRepository;
import com.github.fabioscp0.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@GetMapping
	public List<Permissao> listar(){
		return permissaoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Permissao> buscar(@PathVariable Long id){
		Optional<Permissao> permissao = permissaoRepository.findById(id);
		if(permissao.isPresent()) return ResponseEntity.ok(permissao.get());
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Permissao permissao) {
		cadastroPermissaoService.salvar(permissao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Permissao> atualizar(@PathVariable Long id, @RequestBody Permissao permissao){
		Optional<Permissao> permissaoAtual = permissaoRepository.findById(id);
		if(permissaoAtual.isPresent()) {
			BeanUtils.copyProperties(permissao, permissaoAtual.get(),"id");
			Permissao permissaoSalvar = cadastroPermissaoService.salvar(permissaoAtual.get());
			return ResponseEntity.ok(permissaoSalvar);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Permissao> excluir(@PathVariable Long id){
		try {
			cadastroPermissaoService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
