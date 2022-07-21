package com.github.fabioscp0.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.github.fabioscp0.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries{
	
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//Query no arquivo orm.xml
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
	
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinhaId);
}
