package com.example.Minesweeper.service;

import com.example.Minesweeper.model.Tile;
import com.example.Minesweeper.model.GameBoard;
import com.example.Minesweeper.repo.TileRepo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TileService {
    private final TileRepo tileRepo;

    public TileService(TileRepo tileRepo) {this.tileRepo = tileRepo;}

    public void generateTiles(GameBoard gameBoard) {
        for (int i = 1; i < gameBoard.getNumRows() + 1; i++) {
            for (int j = 1; j < gameBoard.getNumCols() + 1; j++) {
                tileRepo.save(new Tile(gameBoard, i, j));
            }
        }
        int minesLeft = gameBoard.getMineCount();
        while (minesLeft > 0){
           Random random = new Random();
           Tile tile = tileRepo.getReferenceById(random.nextLong(gameBoard.getTilesSum())+1);
           if(!tile.isMine()){
               tile.setMine(true);
               tileRepo.save(tile);
               minesLeft--;
           }
        }
    }

}
