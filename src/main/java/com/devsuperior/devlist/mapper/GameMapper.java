package com.devsuperior.devlist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.devsuperior.devlist.dto.GameDTO;
import com.devsuperior.devlist.model.Game;
import com.devsuperior.devlist.projection.GameProjection;

@Mapper
public interface GameMapper {
	
	GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);
	
	public GameDTO toDTO(Game game);
	public GameDTO toDTO(GameProjection game);
	public Game toModel(GameDTO game);

}
