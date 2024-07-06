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
    Minesweeper minesweeper;
    int tileRow;
    int tileColumn;
    boolean mine;
    String tileText;

    public Tile(Minesweeper minesweeper, int row, int column) {
        this.minesweeper = minesweeper;
        this.tileRow = row;
        this.tileColumn = column;
        this.mine = false;
        this.tileText = "";
    }

    public void resetTile() {
        this.mine = false;
        this.tileText = "";
    }
}