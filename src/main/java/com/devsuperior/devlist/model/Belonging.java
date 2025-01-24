package com.devsuperior.devlist.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "tb_belonging") @AllArgsConstructor @NoArgsConstructor @Data
public class Belonging {
	
	@EmbeddedId
	private BelongingPK id = new BelongingPK();;
	
	private Integer position;

	public Belonging(Game game, GameList list, Integer position) {
		this.id.setGame(game);
		this.id.setList(list);
		this.position = position;
	}
}
