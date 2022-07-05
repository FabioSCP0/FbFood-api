package com.github.fabioscp0.domain.repository;

import java.util.List;

import com.github.fabioscp0.domain.model.Cidade;

public interface CidadeRepository {
	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Cidade cidade);
}
