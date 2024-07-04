import {Component, OnInit} from '@angular/core';
import {GameBoard} from "./gameBoard";
import {GameboardService} from "./gameboard.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  standalone: true
})
export class AppComponent implements OnInit{
  // @ts-ignore
  public gameBoards: GameBoard[];

  constructor(private gameboardService: GameboardService) { }

  ngOnInit() {
    this.getGameBoards();
  }

  public getGameBoards(): void {
    this.gameboardService.getAllGameBoards().subscribe(
      (response: GameBoard[]) => {
        this.gameBoards = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

}
