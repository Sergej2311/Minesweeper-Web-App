package com.example.Minesweeper.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinesweeperRepo extends JpaRepository<com.example.Minesweeper.model.Minesweeper, Long> {

}
