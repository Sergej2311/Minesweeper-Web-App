import {Component, OnInit} from '@angular/core';
import {Minesweeper, Tile} from "./minesweeper";
import {MinesweeperService} from "./minesweeper.service";
import {NgFor, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";
import {TileService} from "./tile.service";

@Component({
  selector: 'app-game-board',
  standalone: true,
  imports: [
    NgFor,
    FormsModule,
    NgIf
  ],
  templateUrl: './minesweeper.component.html',
  styleUrl: './minesweeper.component.css'
})
export class MinesweeperComponent implements OnInit{
  public minesweeper!: Minesweeper;
  public tiles: Tile[] = [];

  constructor(private minesweeperService: MinesweeperService, private tileService: TileService) { }

  ngOnInit() {
    this.createGame();
  }

  public loadGame() {
    this.getMinesweeper();
    this.getTiles();
    this.getMinesweeper();
  }

  public getMinesweeper(): void {
    this.minesweeperService.getMinesweeper().subscribe(
      (response: Minesweeper) => {
        this.minesweeper = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getTiles(): void {
    this.tileService.getTiles().subscribe(
      (response: Tile[]) => {
        this.tiles = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public createGame(): void {
    this.minesweeperService.startMinesweeper().subscribe(
      () => {
        this.loadGame();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public leftClickTile(tileId: number, gameboard: Minesweeper): void {
    this.minesweeperService.leftClickTile(tileId, gameboard).subscribe(
      () => {
        this.getMinesweeper();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
