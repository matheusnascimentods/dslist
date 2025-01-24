package com.devsuperior.devlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.devsuperior.devlist.dto.GameListDTO;
import com.devsuperior.devlist.model.GameList;

@Mapper
public interface GameListMapper {

	GameListMapper INSTANCE = Mappers.getMapper(GameListMapper.class);
	
	public GameListDTO toDTO(GameList list);
	public GameList toModel(GameListDTO list);
}
