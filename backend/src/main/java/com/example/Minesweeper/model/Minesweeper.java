package com.example.Minesweeper.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Minesweeper")
@Setter
@Getter
public class Minesweeper {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "minesweeper_generator")
    @SequenceGenerator(name = "minesweeper_generator", sequenceName = "minesweeper_seq", allocationSize = 1)
    private Long id;

    // this entity holds the attributes of a minesweeper game
    private int numRows;
    private int numCols;
    private int tilesSum;
    private int mineCount;
    private int tilesClicked;
    boolean gameWon;
    boolean gameOver;

    // constructor when generating a game
    public Minesweeper() {
        this.numRows = 8;
        this.numCols = numRows;
        this.tilesSum = numCols*numCols;
        this.mineCount = 10;
        this.tilesClicked = 0;
        this.gameOver = false;
        this.gameWon = false;
    }

    // resets a minesweeper for a new game
    public void resetMinesweeper() {
        this.numRows = 8;
        this.numCols = numRows;
        this.tilesSum = numCols*numCols;
        this.mineCount = 10;
        this.tilesClicked = 0;
        this.gameOver = false;
        this.gameWon = false;
    }

    // increases the click counter
    public void clickTile() {
        this.tilesClicked++;
        if (this.tilesClicked == this.tilesSum) {   // checks if all tiles are clicked
            this.gameWon = true;                    // if so, the game is won
        }
    }
}
