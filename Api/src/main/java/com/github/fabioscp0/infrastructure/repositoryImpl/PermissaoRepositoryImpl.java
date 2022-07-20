package com.github.fabioscp0.infrastructure.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.fabioscp0.domain.model.Permissao;
import com.github.fabioscp0.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> listar() {
		TypedQuery<Permissao> query = manager.createQuery("from Permissao", Permissao.class);
		return query.getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Override
	@Transactional
	public void remover(Permissao permissao) {
		permissao = buscar(permissao.getId());
		manager.remove(permissao);		
	}
}
