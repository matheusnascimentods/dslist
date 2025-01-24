package com.devsuperior.devlist.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.devlist.dto.GameListDTO;
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
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> data = repository.findAll();
		
		return data.stream().map(mapper::toDTO).collect(Collectors.toList());
	}
	
	@Transactional
	public void replacement (Long listId, int sourceIndex, int destinationIndex) {
		List<GameProjection> list = gameRepository.searchByList(listId);
		
		GameProjection removed = list.remove(sourceIndex);
		list.add(destinationIndex, removed);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			repository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
