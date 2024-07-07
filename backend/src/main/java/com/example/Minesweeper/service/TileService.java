package com.example.Minesweeper.service;

import com.example.Minesweeper.model.Tile;
import com.example.Minesweeper.model.Minesweeper;
import com.example.Minesweeper.repo.TileRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TileService {
    private final TileRepo tileRepo;
    private final MinesweeperService minesweeperService;

    public TileService(TileRepo tileRepo, MinesweeperService minesweeperService) {this.tileRepo = tileRepo;
        this.minesweeperService = minesweeperService;
    }

    // generates all tiles, sets random mines and calculates the mines surrounding
    public void generateTiles(Minesweeper minesweeper) {
        int tileCount = 0;                                                          // counts how many tiles are generated
        for (int i = 1; i < minesweeper.getNumRows() + 1; i++) {                    // loops through every row
            for (int j = 1; j < minesweeper.getNumCols() + 1; j++) {                // loops through column
                Tile savedTile = new Tile(minesweeper, i, j);                       // generates a new tile with current row and column
                tileCount++;                                                        // increases the tileCount
                Optional<Tile> existingTile = tileRepo.findById((long) tileCount);
                if (existingTile.isPresent() ) {                                    // checks if the generates tile already exists
                    existingTile.get().resetTile();                                 // resets the existing tile
                    savedTile = existingTile.get();                                 // overwrites the tile to save in the db
                }
                tileRepo.save(savedTile);                                           // saves tile in the db
            }
        }

        int minesLeft = minesweeper.getMineCount();                                                 // gets how many mines to place
        while (minesLeft > 0){                                                                      // repeats while mines are left
           Random random = new Random();                                                            // sets a random
           Tile tile = tileRepo.getReferenceById(random.nextLong(minesweeper.getTilesSum())+1);     // loads a random tile from the db
           if(!tile.isMine()){                                                                      // checks if tile is already a mine
               tile.setMine(true);                                                                  // sets a mine on the tile
               tileRepo.save(tile);                                                                 // saves the tile with mine to the db
               minesLeft--;                                                                         // decreases mines to place by one
           }
        }

        List<Tile> allTiles = tileRepo.findAll();                                                   // loads all tiles from the db
        for (Tile tile : allTiles) {                                                                // iterates through all tiles
            int minesAround = 0;                                                                    // sets mines around to 0
            List<Long> tilesAround = tile.getTileIdsAround();                                       // saves all tileIds around this tile in a list
            for (Long tileAround : tilesAround) {                                                   // iterates through each tile surround this one
                if(tileRepo.getReferenceById(tileAround).isMine()){                                 // checks if there is a mine
                    minesAround++;
                }
            }
            tile.setMinesAround(minesAround);                                                       // sets minesAround attribute to the count
            tileRepo.save(tile);                                                                    // saves this tile in the db
        }
    }

    // reveals the number of surrounding mines
    public void revealMinesAroundTile(Long id) {
        Tile revealedTile = tileRepo.getReferenceById(id);                                          // loads the tile to reveal on
        revealedTile.revealMines();                                                                 // reveals the number to display it
        minesweeperService.clickTile(revealedTile);                                                 // clicks to increase the click counter
        tileRepo.save(tileRepo.getReferenceById(id));                                               // saves the revealed tile to the db
    }

    // sets a flag on a mine tile
    public void setFlag(Long id) {
        Tile flagedTile = tileRepo.getReferenceById(id);                                            // loads the tile to set a flag
        minesweeperService.clickTile(tileRepo.getReferenceById(id));                                // clicks to increase the click counter
        flagedTile.setTileText("🚩");                                                               // sets a flag emoji to display it
        tileRepo.save(flagedTile);                                                                  // saves the tile with flag
    }

    // reveals all mines on board
    public void solve() {
        List<Tile> allTiles = tileRepo.findAll();                                                   // loads all tiles from db
        for (Tile tile : allTiles) {                                                                // iterates through each tile
            if (tile.isMine()) {                                                                    // checks if tile is a mine
                tile.setTileText("💣");                                                             // sets a bomb emoji to display it
                tileRepo.save(tile);                                                                // saves the tile to the db
            }
        }
    }
}
