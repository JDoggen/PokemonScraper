package com.pokemon.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemon.dao.IPokemonMoveDao;
import com.pokemon.model.*;

@Service
public class PokemonMoveService implements IPokemonMoveService {
	
	@Autowired
	private IPokemonMoveDao iPokemonMoveDao;

	@Override
	public Iterable<PokemonMove> scrapeMoves() {
		Document document = null;
		try {
			document = Jsoup.connect("https://pokemondb.net/move/generation/1")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246")
					.get();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		Elements tbody = document.getElementsByTag("tbody");
		Elements elements = tbody.get(0).getElementsByTag("tr");
		ArrayList<PokemonMove> pokemonMoves = new ArrayList<>();
		for(Element element : elements) {
			System.out.println(pokemonMoves.size());
			PokemonMove pokemonMove = new PokemonMove();
			pokemonMove.setName(element.getElementsByIndexEquals(0).text());
			pokemonMove.setType(element.getElementsByIndexEquals(1).text());
			try {
				pokemonMove.setPower(Integer.parseInt(element.getElementsByIndexEquals(3).text()));
			} catch(Exception e){
				pokemonMove.setPower(-1);
			}
			
			try {
				pokemonMove.setAccuracy(Integer.parseInt(element.getElementsByIndexEquals(4).text()));
			} catch(Exception e){
				pokemonMove.setAccuracy(-1);
			}
			
			try {
				pokemonMove.setPowerPoints(Integer.parseInt(element.getElementsByIndexEquals(5).text()));
			} catch(Exception e) {
				pokemonMove.setPowerPoints(-1);
			}
			pokemonMove.setEffect(element.getElementsByIndexEquals(6).text());
			pokemonMoves.add(pokemonMove);
		}
		return this.iPokemonMoveDao.saveAll(pokemonMoves);
	}

}
