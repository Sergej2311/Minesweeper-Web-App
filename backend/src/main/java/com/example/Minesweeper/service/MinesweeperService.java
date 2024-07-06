package com.example.Minesweeper.service;

import com.example.Minesweeper.model.Minesweeper;
import com.example.Minesweeper.repo.MinesweeperRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MinesweeperService {
    private final MinesweeperRepo minesweeperRepo;

    public MinesweeperService(MinesweeperRepo minesweeper) {this.minesweeperRepo = minesweeper;}

    public Minesweeper startGame () {
        // Creates game to save
       Minesweeper savedGame = new Minesweeper();

        // Check if a Game already exists (max. 1 Game at a Time possible)
        Optional<Minesweeper> existingGame = minesweeperRepo.findById(1L);
        if(existingGame.isPresent()) {
            existingGame.get().resetMinesweeper();    // reset Game if present
            savedGame = existingGame.get();         // overwrite Game to save
        }
        minesweeperRepo.save(savedGame);
        return savedGame;
    }

    public void clickTile (Minesweeper minesweeper) {
        minesweeper.clickTile();
        minesweeperRepo.save(minesweeper);
    }

    public void looseGame(Minesweeper minesweeper) {
        minesweeper.setGameOver(true);
        minesweeperRepo.save(minesweeper);
    }
}
