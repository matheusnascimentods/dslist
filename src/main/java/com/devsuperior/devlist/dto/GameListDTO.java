package com.devsuperior.devlist.dto;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.devsuperior.devlist.model.GameList;

public class GameListDTO {
	
	private Long id;
	private String name;	
	
	public GameListDTO() {
	}
	
	public GameListDTO(GameList gameList) {
		BeanUtils.copyProperties(gameList, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameListDTO other = (GameListDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
