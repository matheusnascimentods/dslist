package com .devsuperior.devlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.devlist.dto.BelongingDTO;
import com.devsuperior.devlist.dto.GameListDTO;
import com.devsuperior.devlist.dto.MoveDTO;
import com.devsuperior.devlist.projection.GameProjection;
import com.devsuperior.devlist.services.GameListService;
import com.devsuperior.devlist.services.GameService;

import jakarta.validation.Valid;

@RestController @RequestMapping(value = "lists")
public class GameListController {
	
	@Autowired
	private GameListService service;
	
	@Autowired
	private GameService gameService;
	
	@PostMapping
	public ResponseEntity<GameListDTO> post(@RequestBody @Valid GameListDTO dto, UriComponentsBuilder uri) {
		return service.post(dto, uri);
	}
	
	@PostMapping(value = "/add")
	public List<GameProjection> add(@RequestBody @Valid BelongingDTO dto) {
		return service.add(dto);
	}

	@GetMapping
	public List<GameListDTO> get() {		
		return service.get();
	}	
	
	@GetMapping(value = "/{listId}/games")
	public List<GameProjection> findByList(@PathVariable Long listId) {		
		return gameService.getList(listId);
	}	
	
	@PostMapping(value = "/{listId}/replacement")
	public void replacement(@PathVariable Long listId, @Valid @RequestBody MoveDTO dto) {	
		service.replacement(listId, dto.getSourceIndex(), dto.getDestinationIndex());
	}	
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
