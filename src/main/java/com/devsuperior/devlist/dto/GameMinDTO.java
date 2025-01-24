package com.devsuperior.devlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class GameMinDTO {

	private Long id;
	private String title;
	private Integer year;
	private String genre;
	private String imgUrl;
	private String shortDescription;
}
