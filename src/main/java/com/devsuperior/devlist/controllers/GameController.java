package com.devsuperior.devlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.devsuperior.devlist.dto.GameDTO;
import com.devsuperior.devlist.services.GameService;

import jakarta.validation.Valid;

@RestController @RequestMapping(value = "games")
public class GameController {
	
	@Autowired
	private GameService service;

	@GetMapping
	public List<GameDTO> get(@RequestParam(required = false) Long id) {		
		return service.get(id);
	}
	
	@PostMapping @ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<GameDTO> post(@Valid @RequestBody GameDTO dto, UriComponentsBuilder uri) {
		return service.post(dto, uri);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GameDTO> put(@Valid @RequestBody GameDTO dto, @PathVariable Long id) {
		return service.put(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
