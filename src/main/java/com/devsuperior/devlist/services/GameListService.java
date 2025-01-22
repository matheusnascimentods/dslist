package com.devsuperior.devlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.devlist.dto.GameListDTO;
import com.devsuperior.devlist.model.GameList;
import com.devsuperior.devlist.repositories.GameListRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository repository;
	
	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> lists = repository.findAll();
		
		return lists.stream().map(list -> new GameListDTO(list)).toList();
	}
}
