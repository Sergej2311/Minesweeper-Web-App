package com.example.Minesweeper.service;

import com.example.Minesweeper.model.MineTile;
import com.example.Minesweeper.model.GameBoard;
import com.example.Minesweeper.repo.MineTileRepo;
import org.springframework.stereotype.Service;

@Service
public class MineTileService {
    private final MineTileRepo mineTileRepo;

    public MineTileService(MineTileRepo mineTileRepo) {this.mineTileRepo = mineTileRepo;}

    public void saveTile(GameBoard gameBoard) {
        for (int i = 1; i < gameBoard.getNumRows() + 1; i++) {
            for (int j = 1; j < gameBoard.getNumCols() + 1; j++) {
                mineTileRepo.save(new MineTile(gameBoard, i, j));
            }
        }
    }

}
