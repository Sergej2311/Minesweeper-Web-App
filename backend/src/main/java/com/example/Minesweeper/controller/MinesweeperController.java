package com.example.Minesweeper.controller;

import com.example.Minesweeper.model.Tile;
import com.example.Minesweeper.repo.Minesweeper;
import com.example.Minesweeper.repo.TileRepo;
import com.example.Minesweeper.service.GameBoardService;
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
    private Minesweeper minesweeper;
    @Autowired
    private GameBoardService gameBoardService;

    @Autowired
    private TileRepo tileRepo;
    @Autowired
    private TileService tileService;

    //called when starting a new Game
    @GetMapping("/startGame")
    public ResponseEntity<com.example.Minesweeper.model.Minesweeper> startGame(){
        com.example.Minesweeper.model.Minesweeper newGame = gameBoardService.startGame();       // starts new game
        tileService.generateTiles(newGame);                     // generates the mineTiles
        return new ResponseEntity<>(newGame, HttpStatus.OK);    // returns new game and httpstatus
    }

    @PostMapping("createGame")
    public ResponseEntity<com.example.Minesweeper.model.Minesweeper> createGame(@RequestBody com.example.Minesweeper.model.Minesweeper minesweeper){
        this.minesweeper.save(minesweeper);
        tileService.generateTiles(minesweeper);
        return new ResponseEntity<>(minesweeper, HttpStatus.OK);
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


    @GetMapping("/getAllGames")
    public ResponseEntity<List<com.example.Minesweeper.model.Minesweeper>> getAllMinesweepers() {
        try {
            List<com.example.Minesweeper.model.Minesweeper> minesweeperList = new ArrayList<>(minesweeper.findAll());

            if (minesweeperList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(minesweeperList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
