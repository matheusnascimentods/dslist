package com.devsuperior.devlist.services;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.devlist.dto.GameDTO;
import com.devsuperior.devlist.exception.exceptions.GameListNotFoundException;
import com.devsuperior.devlist.exception.exceptions.GameNotFoundException;
import com.devsuperior.devlist.mapper.GameMapper;
import com.devsuperior.devlist.model.Game;
import com.devsuperior.devlist.projection.GameProjection;
import com.devsuperior.devlist.repositories.GameListRepository;
import com.devsuperior.devlist.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;
	
	@Autowired
	private GameListRepository gameListRepository;

	private final static GameMapper mapper = GameMapper.INSTANCE;
	
	@Transactional(readOnly = true)
	public List<GameDTO> get(Long id) {
		if (id != null) {
			if (!repository.existsById(id)) { throw new GameNotFoundException(); }
	
			List<Game> data = repository.findById(id).map(Collections::singletonList).orElse(Collections.emptyList());
			return data.stream().map(mapper::toDTO).collect(Collectors.toList());
		}
		List<Game> data = repository.findAll();
		return data.stream().map(mapper::toDTO).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<GameProjection> getList(Long id) {
		if(!gameListRepository.existsById(id)) { throw new GameListNotFoundException(); }
		
		return repository.searchByList(id);
	}
	
	@Transactional
	public ResponseEntity<GameDTO> post(GameDTO dto, UriComponentsBuilder uriBuilder) {
		Game gameToSave = mapper.toModel(dto);
		Game createdGame = repository.save(gameToSave);
		
		URI uri = uriBuilder.path("/games/{id}").buildAndExpand(createdGame.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.toDTO(createdGame));
	}
	
	@Transactional
	public ResponseEntity<GameDTO> put(Long id, GameDTO dto) {
		if (!repository.existsById(id)) { throw new GameNotFoundException(); }
		
		Game updatedGame = repository.findById(id).map(game -> {
			BeanUtils.copyProperties(dto, game, "id");
			return repository.save(game);
		}).get();
		
		return ResponseEntity.ok().body(mapper.toDTO(updatedGame));
	}
	
	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) { throw new GameNotFoundException(); }
		
		gameListRepository.deleteBelonging(id);
		repository.deleteById(id);
	}
}
