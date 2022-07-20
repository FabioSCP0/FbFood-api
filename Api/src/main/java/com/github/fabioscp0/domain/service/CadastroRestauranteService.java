package com.github.fabioscp0.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabioscp0.domain.exception.EntidadeNaoEncontradaException;
import com.github.fabioscp0.domain.model.Cozinha;
import com.github.fabioscp0.domain.model.Restaurante;
import com.github.fabioscp0.domain.repository.CozinhaRepository;
import com.github.fabioscp0.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		if(restaurante.getCozinha() == null) throw new EntidadeNaoEncontradaException(String.format("Restaurante %s não possui cozinha cadastrada",restaurante.getNome()));
		Long cozinhaId = restaurante.getCozinha().getId();
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
		if(cozinha.isEmpty()) throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		
		restaurante.setCozinha(cozinha.get());
		return restauranteRepository.save(restaurante);
	}
	
	public void excluir(Long id) {
		try {
			restauranteRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não pode ser encontrada", id));
		}
	}
}
