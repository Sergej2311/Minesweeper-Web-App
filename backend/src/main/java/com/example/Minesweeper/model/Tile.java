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

    @ManyToOne
    Minesweeper minesweeper;
    int tileRow;
    int tileColumn;
    boolean mine;
    boolean clicked;
    String tileText;

    public Tile(Minesweeper minesweeper, int row, int column) {
        this.minesweeper = minesweeper;
        this.tileRow = row;
        this.tileColumn = column;
        this.mine = false;
        this.clicked = false;
        this.tileText = "";
    }

    public void resetTile() {
        this.mine = false;
        this.clicked = false;
        this.tileText = "";
    }

    public List<Long> getTileIdsAround() {
        List<Long> tileIdsAround = new ArrayList<>();
        List<Long> prepIds = List.of(1L, 7L, 8L, 9L);  // prepared List of IDs

        for (Long prepId : prepIds) {
            if(this.id+prepId <= 64){
                tileIdsAround.add(this.id+prepId);
            }
            if(this.id-prepId >= 1){
                tileIdsAround.add(this.id-prepId);
            }
        }

        return tileIdsAround;
    }
}