package com.example.Minesweeper.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="GameBoard")
@Setter
@Getter
public class GameBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game-board_generator")
    @SequenceGenerator(name = "game-board_generator", sequenceName = "game-board_seq", allocationSize = 1)
    private Long id;


    private int numRows;

    private int numCols;

    private int mineCount;

    private int tilesClicked;

    boolean gameOver;

    public GameBoard() {
        this.numRows = 8;
        this.numCols = numRows;
        this.mineCount = 10;
        this.tilesClicked = 0;
        this.gameOver = false;
    }
}
