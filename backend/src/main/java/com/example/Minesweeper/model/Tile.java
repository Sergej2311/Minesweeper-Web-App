package com.example.Minesweeper.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MineTile")
@NoArgsConstructor
@Setter
@Getter
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tile_generator")
    @SequenceGenerator(name = "tile_generator", sequenceName = "tile_seq", allocationSize = 1)
    private Long id;

    // this entity holds the attributes of each tile
    @ManyToOne
    Minesweeper minesweeper;
    int tileRow;
    int tileColumn;
    boolean mine;
    int minesAround;
    boolean clicked;
    String tileText;

    // constructor when generating a tile
    public Tile(Minesweeper minesweeper, int row, int column) {
        this.minesweeper = minesweeper;
        this.tileRow = row;
        this.tileColumn = column;
        this.mine = false;
        this.clicked = false;
        this.tileText = "";
    }

    // resets a tile for a new game
    public void resetTile() {
        this.mine = false;
        this.clicked = false;
        this.tileText = "";
    }

    // reveals the mine information through the tileText
    public void revealMines() {
        this.setTileText(String.valueOf(this.minesAround));
    }

    // gives a list of Ids back, which are around this tile
    public List<Long> getTileIdsAround() {
        List<Long> tileIdsAround = new ArrayList<>();
        List<Long> supressedList = new ArrayList<>();
        List<Long> leftBoarderTiles = List.of(1L, 9L, 17L, 25L, 33L, 41L, 49L, 57L);        // list of tiles which are on the left boarder
        List<Long> rightBoarderTiles = List.of(8L, 16L, 24L, 32L, 40L, 48L, 56L, 64L);      // list of tiles which are on the right boarder
        List<Long> prepIds = List.of(1L, 7L, 8L, 9L, -1L, -7L, -8L, -9L);                   // prepared List to calculate surrounding tiles

        if(leftBoarderTiles.contains(this.id)) {
            supressedList.add(-9L);
            supressedList.add(-1L);     // tiles on the left boarder have to ignore these tiles to work correct
            supressedList.add(+7L);
        }
        else if(rightBoarderTiles.contains(this.id)) {
            supressedList.add(-7L);
            supressedList.add(+1L);     // tiles on the right boarder have to ignore these tiles to work correct
            supressedList.add(+9L);
        }

        for (Long prepId : prepIds) {                           // loops through prepared list to calculate surrounding tiles
            if(!supressedList.contains(prepId)){                // checks if this tile needs to be ignored
                if(this.id+prepId >=1 && this.id+prepId <= 64)  // checks if tile is in range of the board
                    tileIdsAround.add(this.id+prepId);          // adds tileId to tilesAround
            }
        }
        return tileIdsAround;
    }
}