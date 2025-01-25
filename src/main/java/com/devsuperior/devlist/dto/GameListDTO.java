package com.devsuperior.devlist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class GameListDTO {
	
	private Long id;
	
	@NotBlank(message = "Game list name is required")
	private String name;
}
