package com.example.Minesweeper.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Minesweeper")
@Setter
@Getter
public class Minesweeper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game-board_generator")
    @SequenceGenerator(name = "game-board_generator", sequenceName = "game-board_seq", allocationSize = 1)
    private Long id;

    private int numRows;
    private int numCols;
    private int tilesSum;
    private int mineCount;
    private int tilesClicked;
    boolean gameOver;

    public Minesweeper() {
        this.numRows = 4;
        this.numCols = numRows;
        this.tilesSum = numCols*numCols;
        this.mineCount = 4;
        this.tilesClicked = 0;
        this.gameOver = false;
    }

    public void resetGameBoard() {
        this.numRows = 4;
        this.numCols = numRows;
        this.tilesSum = numCols*numCols;
        this.mineCount = 4;
        this.tilesClicked = 0;
        this.gameOver = false;
    }
}
