package com.github.fabioscp0.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.fabioscp0.domain.exception.EntidadeEmUsoException;
import com.github.fabioscp0.domain.exception.EntidadeNaoEncontradaException;
import com.github.fabioscp0.domain.model.Cidade;
import com.github.fabioscp0.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.salvar(cidade);
	}
	
	public void excluir(Long id) {
		try {
			cidadeRepository.remover(id);
		} catch (EntidadeEmUsoException e) {
			throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser encontrada", id));
		} catch(EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade de código %d não pode ser encontrada", id));
		}
	}
}
