package com.pokemon.dao;

import org.springframework.data.repository.CrudRepository;

import com.pokemon.model.PokemonMove;

public interface IPokemonMoveDao extends CrudRepository<PokemonMove, Long>{
	
	

}
