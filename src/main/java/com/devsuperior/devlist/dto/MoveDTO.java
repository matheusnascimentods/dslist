package com.devsuperior.devlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class MoveDTO {

	private Integer sourceIndex;
	private Integer destinationIndex;
}
