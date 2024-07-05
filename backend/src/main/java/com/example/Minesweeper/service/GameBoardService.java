package com.example.Minesweeper.service;

import com.example.Minesweeper.model.GameBoard;
import com.example.Minesweeper.repo.GameBoardRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameBoardService {
    private final GameBoardRepo gameBoardRepo;

    public GameBoardService(GameBoardRepo gameBoardRepo) {this.gameBoardRepo = gameBoardRepo;}

    public GameBoard startGame () {
        // Creates game to save
        GameBoard savedGame = new GameBoard();

        // Check if a Game already exists (max. 1 Game at a Time possible)
        Optional<GameBoard> existingGame = gameBoardRepo.findById(1L);
        if(existingGame.isPresent()) {
            existingGame.get().resetGameBoard();    // reset Game if present
            savedGame = existingGame.get();         // overwrite Game to save
        }
        gameBoardRepo.save(savedGame);
        return savedGame;
    }
}
