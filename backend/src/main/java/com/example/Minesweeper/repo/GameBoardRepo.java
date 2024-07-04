package com.example.Minesweeper.repo;

import com.example.Minesweeper.model.GameBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameBoardRepo extends JpaRepository<GameBoard, Long> {

}
