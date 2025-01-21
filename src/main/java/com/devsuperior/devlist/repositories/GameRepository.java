package com.devsuperior.devlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.devlist.model.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
