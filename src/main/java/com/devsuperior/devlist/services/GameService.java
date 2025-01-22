package com.devsuperior.devlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.devlist.dto.GameDTO;
import com.devsuperior.devlist.dto.GameMinDTO;
import com.devsuperior.devlist.model.Game;
import com.devsuperior.devlist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		List<Game> games = repository.findAll();
		List<GameMinDTO> gamesDTO = games.stream().map(game -> new GameMinDTO(game)).toList();
		
		return gamesDTO;
	}

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game game = repository.findById(id).get();
		GameDTO dto = new GameDTO(game);
		
		return dto;
	}
}
