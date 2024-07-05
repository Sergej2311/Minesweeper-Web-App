package com.example.Minesweeper.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="MineTile")
@NoArgsConstructor
@Setter
@Getter
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mine-tile_generator")
    @SequenceGenerator(name = "mine-tile_generator", sequenceName = "mine-tile_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    GameBoard gameBoard;
    int tileRow;
    int tileColumn;
    boolean isMine;
    String tileText;

    public Tile(GameBoard gameBoard, int row, int column) {
        this.gameBoard = gameBoard;
        this.tileRow = row;
        this.tileColumn = column;
        this.isMine = false;
        this.tileText = "";
    }

    public void resetTile() {
        this.isMine = false;
        this.tileText = "";
    }
}