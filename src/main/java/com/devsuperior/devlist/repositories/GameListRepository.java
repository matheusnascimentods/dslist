package com.devsuperior.devlist.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.devlist.model.Belonging;
import com.devsuperior.devlist.model.GameList;


public interface GameListRepository extends JpaRepository<GameList, Long>{
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
	void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tb_belonging WHERE game_id = :gameId")
	void deleteGameFromBelonging(Long gameId);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tb_belonging WHERE list_id = :listId")
	void deleteListeFromBelonging(Long listId);
	
	@Modifying
	@Query(nativeQuery = true, value= "SELECT * FROM tb_belonging WHERE list_id = :listId")
	List<Belonging> findBelonging(Long listId);
	
	@Modifying
	@Query(nativeQuery = true, value= "INSERT INTO tb_belonging (list_id, game_id, position) VALUES (:listId, :gameId, :position)")
	void saveInBelonging(Long listId, Long gameId, Integer position);
}
