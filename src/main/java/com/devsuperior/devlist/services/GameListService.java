package com.devsuperior.devlist.services;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.devlist.dto.BelongingDTO;
import com.devsuperior.devlist.dto.GameListDTO;
import com.devsuperior.devlist.exception.exceptions.GameListNotFoundException;
import com.devsuperior.devlist.exception.exceptions.GameNotFoundException;
import com.devsuperior.devlist.mapper.GameListMapper;
import com.devsuperior.devlist.model.GameList;
import com.devsuperior.devlist.projection.GameProjection;
import com.devsuperior.devlist.repositories.GameListRepository;
import com.devsuperior.devlist.repositories.GameRepository;

@Service
public class GameListService {
	
	private final static GameListMapper mapper = GameListMapper.INSTANCE;

	@Autowired
	private GameListRepository repository;
	
	@Autowired 
	private GameRepository gameRepository;
	
	@Transactional
	public List<GameProjection> add(BelongingDTO dto) {
		if (!repository.existsById(dto.getListId())) { throw new GameListNotFoundException(); }
		
		if (!gameRepository.existsById(dto.getGameId())) { throw new GameNotFoundException(); }
		
		int index = repository.findBelonging(dto.getListId()).size() - 1;
		repository.saveInBelonging(dto.getListId(), dto.getGameId(), index);
		
		return gameRepository.searchByList(dto.getListId());
	}
	
	@Transactional
	public ResponseEntity<GameListDTO> post(GameListDTO dto, UriComponentsBuilder uriBuilder) {
		GameList listToCreate = mapper.toModel(dto);
		GameList createdList = repository.save(listToCreate);
		
		URI uri = uriBuilder.path("/lists/{id}").buildAndExpand(mapper.toDTO(createdList)).toUri();
		return ResponseEntity.created(uri).body(mapper.toDTO(createdList));
	}
	
	@Transactional(readOnly = true)
	public List<GameListDTO> get() {
		List<GameList> data = repository.findAll();
		
		return data.stream().map(mapper::toDTO).collect(Collectors.toList());
	}
	
	@Transactional
	public void replacement (Long listId, int sourceIndex, int destinationIndex) {
		if (!repository.existsById(listId)) { throw new GameListNotFoundException(); }
		
		List<GameProjection> list = gameRepository.searchByList(listId);
		
		GameProjection removed = list.remove(sourceIndex);
		list.add(destinationIndex, removed);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			repository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
	
	@Transactional
	public void delete(Long id) {
		if (!repository.existsById(id)) { throw new GameListNotFoundException(); }
		
		repository.deleteListeFromBelonging(id);
		repository.deleteById(id);
	}

	@Transactional
	public void remove(BelongingDTO dto) {
		if (!repository.existsById(dto.getListId())) { throw new GameListNotFoundException(); }
		
		if (!gameRepository.existsById(dto.getGameId())) { throw new GameNotFoundException(); }
		
		repository.deleteInBelonging(dto.getGameId(), dto.getListId());
	}
}
