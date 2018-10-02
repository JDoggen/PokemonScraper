package com.pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.service.IPokemonMoveService;
import com.pokemon.model.PokemonMove;

@CrossOrigin
@RestController
public class PokemonMoveController {
	
	@Autowired
	private IPokemonMoveService iPokemonMoveService;
	
	@GetMapping("/api/pokemonmoves/scrape")
	private Iterable<PokemonMove> scrape(){
		return this.iPokemonMoveService.scrapeMoves();
	}
	

}
