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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.fabioscp0.domain.exception.EntidadeEmUsoException;
import com.github.fabioscp0.domain.exception.EntidadeNaoEncontradaException;
import com.github.fabioscp0.domain.model.Cozinha;
import com.github.fabioscp0.domain.repository.CozinhaRepository;
import com.github.fabioscp0.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}
 
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		if(cozinha.isPresent()) return ResponseEntity.ok(cozinha.get());
		
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/por-nome")
	public ResponseEntity<List<Cozinha>> buscarPorNome(@RequestParam("nome") String nome){
		List<Cozinha> cozinha = cozinhaRepository.findBynome(nome);
		if(!cozinha.isEmpty()) return ResponseEntity.ok(cozinha);
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionar(@RequestBody Cozinha cozinha) {
		cadastroCozinha.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
		if(cozinhaAtual.isPresent()) {
			//cozinhaAtual.setNome(cozinha.getNome());
			BeanUtils.copyProperties(cozinha, cozinhaAtual,"id");
			Cozinha cozinhaSalvar = cadastroCozinha.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaSalvar);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			cadastroCozinha.excluir(id);
			return ResponseEntity.noContent().build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
