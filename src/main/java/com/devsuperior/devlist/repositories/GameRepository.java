package com.devsuperior.devlist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.devlist.model.Game;
import com.devsuperior.devlist.projection.GameProjection;

public interface GameRepository extends JpaRepository<Game, Long>{
	@Query(nativeQuery = true, value = """
			SELECT games.id, games.title, games.game_year AS "year",
			games.genre, games.platforms, games.score, games.img_url AS imgUrl, 
			games.short_description AS shortDescription, games.long_description AS longDescription, tb_belonging.position
			FROM games
			INNER JOIN tb_belonging ON games.id = tb_belonging.game_id
			WHERE tb_belonging.list_id = :listId
			ORDER BY tb_belonging.position
				""")
	List<GameProjection> searchByList(Long listId);
}
