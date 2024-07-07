package com.example.Minesweeper.controller;

import com.example.Minesweeper.model.Minesweeper;
import com.example.Minesweeper.model.Tile;
import com.example.Minesweeper.repo.MinesweeperRepo;
import com.example.Minesweeper.repo.TileRepo;
import com.example.Minesweeper.service.MinesweeperService;
import com.example.Minesweeper.service.TileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class MinesweeperController {

    // loads all repos and services to work with the data
    @Autowired
    private MinesweeperRepo minesweeperRepo;
    @Autowired
    private MinesweeperService minesweeperService;
    @Autowired
    private TileRepo tileRepo;
    @Autowired
    private TileService tileService;

    //called when starting a new game
    @GetMapping("/minesweeper/initialize")
    public ResponseEntity<?> firstMinesweeper(){
        Minesweeper newMinesweeper = minesweeperService.startGame();        // starts a new game
        tileService.generateTiles(newMinesweeper);                          // generates the tiles for the new game
        return new ResponseEntity<>(HttpStatus.OK);                         // returns new game and httpstatus
    }

    // called when accessing the data of the minesweeper entity
    @GetMapping("/minesweeper/{id}")
    public ResponseEntity<Minesweeper> getMinesweeper(@PathVariable Long id) {
        if (minesweeperRepo.findById(id).isPresent()) {                     // checks if this minesweeper exists
            Minesweeper minesweeper = minesweeperRepo.findById(id).get();
            return new ResponseEntity<>(minesweeper, HttpStatus.OK);        // returns minesweeper and httpstatus
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // called when accessing the data of all tile entities
    @GetMapping("/tiles/all")
    public ResponseEntity<List<Tile>> getAllMineTiles() {
        List<Tile> tileList = new ArrayList<>(tileRepo.findAll());          // finds all saved tiles from the db
        return new ResponseEntity<>(tileList, HttpStatus.OK);               // returns all tiles in a list
    }

    // called when lft-clicking a tile
    @PostMapping("/tiles/left-click/{id}")
    public ResponseEntity<?> leftClickTile(@PathVariable Long id, @RequestBody Minesweeper minesweeper) {   // needs the tileId and the minesweeper
        if(tileRepo.getReferenceById(id).isMine()){                          // checks if this tile is a mine
            minesweeperService.looseGame(minesweeper);                       // sets gameOver to true
        }
        else{
            tileService.revealMinesAroundTile(id);                          // reveals the mineCount around this tile
            Tile tileToReveal = tileRepo.getReferenceById(id);
            if (tileToReveal.getMinesAround() == 0) {                       // checks if this tile has no surrounding mines
                for (Long tileId : tileToReveal.getTileIdsAround()) {       // iterates through surrounding tiles
                    tileService.revealMinesAroundTile(tileId);              // reveals the mineCount for every surrounding tile
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // called when right-clicking a tile
    @GetMapping("/tiles/right-click/{id}")
    public ResponseEntity<?> rightClickTile(@PathVariable Long id) {
        if(tileRepo.getReferenceById(id).isMine()){                         // checks if this tile is a mine
            tileService.setFlag(id);                                        // if so, then sets a flag
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // called when solving a game by showing the mines
    @GetMapping("/tiles/solve")
    public ResponseEntity<?> solve() {
        tileService.solve();                                                 // solves the game
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
