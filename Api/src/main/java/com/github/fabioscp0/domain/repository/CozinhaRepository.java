package com.github.fabioscp0.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.fabioscp0.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

//	List<Cozinha> consultaPorNome(String nome);
	
}
