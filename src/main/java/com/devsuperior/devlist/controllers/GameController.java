package com.devsuperior.devlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.devlist.dto.GameMinDTO;
import com.devsuperior.devlist.services.GameService;

@RestController @RequestMapping(value = "games")
public class GameController {
	
	@Autowired
	private GameService service;

	@GetMapping
	public List<GameMinDTO> findAll() {
		var result = service.findAll();
		
		return result;
	}
}
