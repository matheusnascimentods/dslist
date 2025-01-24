package com.devsuperior.devlist.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.devlist.dto.GameDTO;
import com.devsuperior.devlist.dto.GameMinDTO;
import com.devsuperior.devlist.mapper.GameMapper;
import com.devsuperior.devlist.model.Game;
import com.devsuperior.devlist.projection.GameProjection;
import com.devsuperior.devlist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;

	private final static GameMapper mapper = GameMapper.INSTANCE;
	
	@Transactional(readOnly = true)
	public List<GameDTO> get() {
		List<Game> data = repository.findAll();
		return data.stream().map(mapper::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public GameDTO getById(Long id) {
		Game game = repository.findById(id).get();	
		return mapper.toDTO(game);
	}
	
	@Transactional(readOnly = true)
	public List<GameDTO> getList(Long id) {
		List<GameProjection> data = repository.searchByList(id);
		return data.stream().map(mapper::toDTO).collect(Collectors.toList());
	}
}
