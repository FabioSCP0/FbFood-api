package com.github.fabioscp0.domain.repository;

import java.util.List;

import com.github.fabioscp0.domain.model.Restaurante;

public interface RestauranteRepository {
	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Long id);
}
