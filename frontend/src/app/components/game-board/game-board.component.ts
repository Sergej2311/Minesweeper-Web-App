import {Component, OnInit} from '@angular/core';
import {Gameboard, Tile} from "./minesweeper";
import {GameBoardService} from "./game-board.service";
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
  templateUrl: './game-board.component.html',
  styleUrl: './game-board.component.css'
})
export class GameBoardComponent implements OnInit{
  public gameBoards: Gameboard[] = [];
  public tiles: Tile[] = [];

  constructor(private gameboardService: GameBoardService, private tileService: TileService) { }

  ngOnInit() {
    this.startGame();
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

  public startGame(): void {
    this.gameboardService.startGame().subscribe(
      (response: Gameboard) => {
        this.getGameBoards();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
