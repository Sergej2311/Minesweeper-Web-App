package com.example.Minesweeper.repo;

import com.example.Minesweeper.model.MineTile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MineTileRepo extends JpaRepository <MineTile, Long> {

}
