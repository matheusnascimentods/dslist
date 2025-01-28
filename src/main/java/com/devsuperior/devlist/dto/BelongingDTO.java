package com.devsuperior.devlist.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class BelongingDTO {
	
	@Min(value= 0, message="gameId must be grather than 0")
	private Long listId;
	
	@Min(value= 0, message="gameId must be grather than 0")	private Long gameId;
}
