package com.devsuperior.devlist.dto;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class MoveDTO {

	@Min(value= 0, message="sourceIndex must be greater than or equal to zero")
	private Integer sourceIndex;
	
	@Min(value= 0, message="destinationIndex must be greater than or equal to zero")
	private Integer destinationIndex;
}
