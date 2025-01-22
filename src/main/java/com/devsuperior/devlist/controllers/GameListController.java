package com.devsuperior.devlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.devlist.dto.GameListDTO;
import com.devsuperior.devlist.dto.GameMinDTO;
import com.devsuperior.devlist.services.GameListService;
import com.devsuperior.devlist.services.GameService;

@RestController @RequestMapping(value = "lists")
public class GameListController {
	
	@Autowired
	private GameListService service;
	
	@Autowired
	private GameService gameService;

	@GetMapping
	public List<GameListDTO> findAll() {		
		return service.findAll();
	}	
	
	@GetMapping(value = "/{listId}/games")
	public List<GameMinDTO> findByList(@PathVariable Long listId) {		
		return gameService.findByList(listId);
	}	
}
