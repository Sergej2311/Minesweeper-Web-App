package com.example.Minesweeper.controller;

import com.example.Minesweeper.model.GameBoard;
import com.example.Minesweeper.model.Tile;
import com.example.Minesweeper.repo.GameBoardRepo;
import com.example.Minesweeper.repo.TileRepo;
import com.example.Minesweeper.service.GameBoardService;
import com.example.Minesweeper.service.TileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MinesweeperController {

    @Autowired
    private GameBoardRepo gameBoardRepo;
    @Autowired
    private GameBoardService gameBoardService;

    @Autowired
    private TileRepo tileRepo;
    @Autowired
    private TileService tileService;

    //called when starting a new Game
    @PostMapping("/startGame")
    public ResponseEntity<GameBoard> startGame(){
        GameBoard newGame = gameBoardService.generateGameBoard();   // generates the GameBoard
        tileService.generateTiles(newGame);                     // generates the mineTiles
        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }


    @GetMapping("/getAllGames")
    public ResponseEntity<List<GameBoard>> getAllMinesweepers() {
        try {
            List<GameBoard> gameBoardList = new ArrayList<>(gameBoardRepo.findAll());

            if (gameBoardList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(gameBoardList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllTiles")
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

    @GetMapping("/getMinesweeperById/{id}")
    public ResponseEntity<GameBoard> getMinesweeperById(@PathVariable Long id){
        Optional<GameBoard> minesweeperData = gameBoardRepo.findById(id);

        if (minesweeperData.isPresent()) {
            return new ResponseEntity<>(minesweeperData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addMinesweeper")
    public ResponseEntity<GameBoard> addMinesweeper(@RequestBody GameBoard gameBoard){
         GameBoard gameBoardObj = gameBoardRepo.save(gameBoard);

         return new ResponseEntity<>(gameBoardObj, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMinesweeperById/{id}")
    public ResponseEntity<HttpStatus> deleteMinesweeperById(@PathVariable Long id) {
        gameBoardRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
