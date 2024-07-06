package com.example.Minesweeper.service;

import com.example.Minesweeper.model.Tile;
import com.example.Minesweeper.model.Minesweeper;
import com.example.Minesweeper.repo.TileRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class TileService {
    private final TileRepo tileRepo;

    public TileService(TileRepo tileRepo) {this.tileRepo = tileRepo;}

    public void generateTiles(Minesweeper minesweeper) {
        int count = 0;
        for (int i = 1; i < minesweeper.getNumRows() + 1; i++) {
            for (int j = 1; j < minesweeper.getNumCols() + 1; j++) {
                Tile savedTile = new Tile(minesweeper, i, j);
                count++;
                Optional<Tile> existingTile = tileRepo.findById((long) count);
                if (existingTile.isPresent() ) {
                    existingTile.get().resetTile();
                    savedTile = existingTile.get();
                }
                tileRepo.save(savedTile);
            }
        }
        int minesLeft = minesweeper.getMineCount();
        while (minesLeft > 0){
           Random random = new Random();
           Tile tile = tileRepo.getReferenceById(random.nextLong(minesweeper.getTilesSum())+1);
           if(!tile.isMine()){
               tile.setMine(true);
               tileRepo.save(tile);
               minesLeft--;
           }
        }
    }

}
