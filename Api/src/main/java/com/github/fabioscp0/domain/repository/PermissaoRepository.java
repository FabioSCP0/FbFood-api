package com.github.fabioscp0.domain.repository;

import java.util.List;

import com.github.fabioscp0.domain.model.Permissao;

public interface PermissaoRepository {
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);
}
