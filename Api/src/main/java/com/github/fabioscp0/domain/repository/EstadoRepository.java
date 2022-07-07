package com.github.fabioscp0.domain.repository;

import java.util.List;

import com.github.fabioscp0.domain.model.Estado;

public interface EstadoRepository {
	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Long id);
}
