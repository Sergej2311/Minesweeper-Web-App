package com.example.Minesweeper.controller;

import com.example.Minesweeper.model.MineTile;
import com.example.Minesweeper.model.GameBoard;
import com.example.Minesweeper.repo.MineTileRepo;
import com.example.Minesweeper.service.MineTileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MineTileController {
    @Autowired
    private MineTileRepo mineTileRepo;
    @Autowired
    private MineTileService mineTileService;

    @PostMapping("/addMineTiles")
    public ResponseEntity<GameBoard> addMinesweeper(@RequestBody GameBoard gameBoard){
        mineTileService.generateTiles(gameBoard);
        return new ResponseEntity<>(gameBoard, HttpStatus.OK);
    }

    @GetMapping("/getAllMineTiles")
    public ResponseEntity<List<MineTile>> getAllMineTiles() {
        try {
            List<MineTile> mineTileList = new ArrayList<>(mineTileRepo.findAll());

            if (mineTileList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(mineTileList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
