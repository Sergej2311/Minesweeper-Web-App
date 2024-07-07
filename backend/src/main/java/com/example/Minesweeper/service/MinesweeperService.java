package com.example.Minesweeper.service;

import com.example.Minesweeper.model.Minesweeper;
import com.example.Minesweeper.model.Tile;
import com.example.Minesweeper.repo.MinesweeperRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MinesweeperService {
    private final MinesweeperRepo minesweeperRepo;

    public MinesweeperService(MinesweeperRepo minesweeper) {this.minesweeperRepo = minesweeper;}

    // starts a new game, only one game at a time is possible
    public Minesweeper startGame () {
        Minesweeper savedGame = new Minesweeper();                           // Creates  a new game to save

        Optional<Minesweeper> existingGame = minesweeperRepo.findById(1L);  // tries to find an existing game
        if(existingGame.isPresent()) {
            existingGame.get().resetMinesweeper();                          // resets the game if its existing
            savedGame = existingGame.get();                                 // overwrite the game to save to the existing one
        }
        minesweeperRepo.save(savedGame);                                    // saves the game in the db
        return savedGame;
    }

    // clicks a tile
    public void clickTile(Tile tile) {
        if(!tile.isClicked()){                                              // checks if this tile was already clicked
            tile.setClicked(true);                                          // sets the clicked attribute to true
            tile.getMinesweeper().clickTile();                              // triggers a click in the minesweeper to count the clicks
        }
    }

    // sets the minesweeper to loose
    public void looseGame(Minesweeper minesweeper) {
        minesweeper.setGameOver(true);                                      // changes gameOver to true
        minesweeperRepo.save(minesweeper);                                  // saves the minesweeper in the db
    }
}
