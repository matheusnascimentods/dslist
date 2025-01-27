package com.devsuperior.devlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.devlist.model.GameList;


public interface GameListRepository extends JpaRepository<GameList, Long>{
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
	void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tb_belonging WHERE game_id = :gameId")
	void deleteBelonging(Long gameId);
}
