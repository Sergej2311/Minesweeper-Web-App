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

    private int numRows;
    private int numCols;
    private int tilesSum;
    private int mineCount;
    private int tilesClicked;
    boolean gameWon;
    boolean gameOver;

    public Minesweeper() {
        this.numRows = 8;
        this.numCols = numRows;
        this.tilesSum = numCols*numCols;
        this.mineCount = 10;
        this.tilesClicked = 0;
        this.gameOver = false;
        this.gameWon = false;
    }

    public void resetMinesweeper() {
        this.numRows = 8;
        this.numCols = numRows;
        this.tilesSum = numCols*numCols;
        this.mineCount = 10;
        this.tilesClicked = 0;
        this.gameOver = false;
        this.gameWon = false;
    }

    public void clickTile() {
        this.tilesClicked++;
        if (this.tilesClicked == this.tilesSum) {
            this.gameWon = true;
        }
    }
}
