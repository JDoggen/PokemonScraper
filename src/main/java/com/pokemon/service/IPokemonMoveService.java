package com.pokemon.service;

import java.util.List;

import com.pokemon.model.PokemonMove;

public interface IPokemonMoveService {
	
	public Iterable<PokemonMove>scrapeMoves();

}
