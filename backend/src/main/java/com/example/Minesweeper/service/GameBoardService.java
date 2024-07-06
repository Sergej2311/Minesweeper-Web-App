package com.example.Minesweeper.service;

import com.example.Minesweeper.repo.Minesweeper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameBoardService {
    private final Minesweeper minesweeper;

    public GameBoardService(Minesweeper minesweeper) {this.minesweeper = minesweeper;}

    public com.example.Minesweeper.model.Minesweeper startGame () {
        // Creates game to save
        com.example.Minesweeper.model.Minesweeper savedGame = new com.example.Minesweeper.model.Minesweeper();

        // Check if a Game already exists (max. 1 Game at a Time possible)
        Optional<com.example.Minesweeper.model.Minesweeper> existingGame = minesweeper.findById(1L);
        if(existingGame.isPresent()) {
            existingGame.get().resetGameBoard();    // reset Game if present
            savedGame = existingGame.get();         // overwrite Game to save
        }
        minesweeper.save(savedGame);
        return savedGame;
    }
}
