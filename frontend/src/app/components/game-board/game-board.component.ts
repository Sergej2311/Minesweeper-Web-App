import {Component, OnInit} from '@angular/core';
import {Gameboard} from "./minesweeper";
import {GameBoardService} from "./game-board.service";
import {DatePipe, NgForOf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-game-board',
  standalone: true,
  imports: [
    NgForOf,
    FormsModule,
    DatePipe
  ],
  templateUrl: './game-board.component.html',
  styleUrl: './game-board.component.css'
})
export class GameBoardComponent implements OnInit{
  // @ts-ignore
  public gameBoards: Gameboard[];

  constructor(private gameboardService: GameBoardService) { }

  ngOnInit() {
    this.getGameBoards();
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
}
