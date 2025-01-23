package com.devsuperior.devlist.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.devlist.dto.GameListDTO;
import com.devsuperior.devlist.model.GameList;
import com.devsuperior.devlist.projection.GameMinProjection;
import com.devsuperior.devlist.repositories.GameListRepository;
import com.devsuperior.devlist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository repository;
	
	@Autowired 
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> lists = repository.findAll();
		
		return lists.stream().map(list -> new GameListDTO(list)).toList();
	}
	
	@Transactional
	public void replacement (Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection removed = list.remove(sourceIndex);
		list.add(destinationIndex, removed);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;
		
		for (int i = min; i <= max; i++) {
			repository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}
}
