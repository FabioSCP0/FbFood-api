package com.github.fabioscp0.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.github.fabioscp0.ApiApplication;
import com.github.fabioscp0.domain.model.Restaurante;
import com.github.fabioscp0.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(ApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setId(1L);
		restaurante1.setNome("Burguer King");
		restaurante1.setTaxaFrete(BigDecimal.valueOf(8.5));
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setId(2L);
		restaurante2.setNome("Mc Donalds");
		restaurante2.setTaxaFrete(BigDecimal.valueOf(7.9));
		
		restauranteRepository.salvar(restaurante1);
		restauranteRepository.salvar(restaurante2);
		
	}
}
