package com.devsuperior.devlist.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class GameDTO {
	
	private Long id;
	
	@NotBlank(message = "Title is required")
	private String title;
	
	@Min(value = 1947, message = "The year must be bigger than 1947")
	private Integer year;
	
	@NotBlank(message = "Genre is required")
	private String genre;
	
	@NotBlank(message = "Plataforms is required")
	private String platforms;
	
	@DecimalMin(message = "Min score is 0.0", value = "0")
	private Double score;
	
	@NotBlank(message = "Image is required")
	private String imgUrl;
	
	@NotBlank(message = "Short description is required")
	@Size(max = 200, message = "Short description cannot exceed 200 characters")
	private String shortDescription;
	
	@NotBlank(message = "Short description is required")
	private String longDescription;
}
