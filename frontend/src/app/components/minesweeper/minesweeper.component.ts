import {Component, OnInit} from '@angular/core';
import {Gameboard, Tile} from "./minesweeper";
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
  public gameBoards: Gameboard[] = [];
  public tiles: Tile[] = [];

  constructor(private gameboardService: MinesweeperService, private tileService: TileService) { }

  ngOnInit() {
    this.createGame();
  }

  public loadGame() {
    this.getGameBoards();
    this.geTiles();
  }

  public getGameBoards(): void {
    this.gameboardService.getGameBoards().subscribe(
      (response: Gameboard[]) => {
        this.gameBoards = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public geTiles(): void {
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
    this.gameboardService.createGame().subscribe(
      () => {
        this.getGameBoards();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public leftClickTile(tileId: number, gameboard: Gameboard): void {
    this.gameboardService.leftClickTile(tileId, gameboard).subscribe(
      () => {
        this.getGameBoards();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
