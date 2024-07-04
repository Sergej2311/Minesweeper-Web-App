package com.example.Minesweeper.repo;

import com.example.Minesweeper.model.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileRepo extends JpaRepository <Tile, Long> {

}
