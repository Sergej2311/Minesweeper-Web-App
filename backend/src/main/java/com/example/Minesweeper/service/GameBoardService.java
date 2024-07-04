package com.example.Minesweeper.service;

import com.example.Minesweeper.model.GameBoard;
import com.example.Minesweeper.repo.GameBoardRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameBoardService {
    private final GameBoardRepo gameBoardRepo;

    public GameBoardService(GameBoardRepo gameBoardRepo) {this.gameBoardRepo = gameBoardRepo;}

    public GameBoard generateMinesweeper() {
        return gameBoardRepo.save(new GameBoard());
    }

    public Optional<GameBoard> findById(Long id) {return gameBoardRepo.findById(id);}
}
