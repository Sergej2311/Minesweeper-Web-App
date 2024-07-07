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

    @Autowired
    private MinesweeperRepo minesweeperRepo;
    @Autowired
    private MinesweeperService minesweeperService;

    @Autowired
    private TileRepo tileRepo;
    @Autowired
    private TileService tileService;

    //creates a new game
    @GetMapping("/createGame")
    public ResponseEntity<?> startGame(){
        Minesweeper newMinesweeper = minesweeperService.startGame();        // starts new game
        tileService.generateTiles(newMinesweeper);                          // generates the mineTiles
        return new ResponseEntity<>(HttpStatus.OK);         // returns new game and httpstatus
    }

    @GetMapping("/minesweeper/{id}")
    public ResponseEntity<Minesweeper> getMinesweeper(@PathVariable Long id) {
        if (minesweeperRepo.findById(id).isPresent()) {
            Minesweeper minesweeper = minesweeperRepo.findById(id).get();
            return new ResponseEntity<>(minesweeper, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tiles/all")
    public ResponseEntity<List<Tile>> getAllMineTiles() {
        try {
            List<Tile> tileList = new ArrayList<>(tileRepo.findAll());

            if (tileList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tileList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tiles/left-click/{id}")
    public ResponseEntity<?> leftClickTile(@PathVariable Long id, @RequestBody Minesweeper minesweeper) {
        // Check if tile was a mine
        if(tileRepo.getReferenceById(id).isMine()){
            minesweeperService.looseGame(minesweeper);  // loose game
        }
        else{
            tileService.countMinesAroundTile(id);
            minesweeperService.clickTile(minesweeper);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tiles/right-click/{id}")
    public ResponseEntity<?> rightClickTile(@PathVariable Long id, @RequestBody Minesweeper minesweeper) {
        if(tileRepo.getReferenceById(id).isMine()){
            tileService.setFlag(id);
            minesweeperService.clickTile(minesweeper);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
