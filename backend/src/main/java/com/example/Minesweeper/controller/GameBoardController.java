package com.example.Minesweeper.controller;

import com.example.Minesweeper.model.GameBoard;
import com.example.Minesweeper.repo.GameBoardRepo;
import com.example.Minesweeper.service.GameBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GameBoardController {

    @Autowired
    private GameBoardRepo gameBoardRepo;
    @Autowired
    private GameBoardService gameBoardService;

    @PostMapping("/startGame")
    public ResponseEntity<GameBoard> startGame(){
        GameBoard newGame = gameBoardService.generateMinesweeper();
        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }


    @GetMapping("/getAllMinesweepers")
    public ResponseEntity<List<GameBoard>> getAllMinesweepers() {
        try {
            List<GameBoard> gameBoardList = new ArrayList<>();
            gameBoardRepo.findAll().forEach(gameBoardList::add);

            if (gameBoardList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(gameBoardList, HttpStatus.OK);
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
