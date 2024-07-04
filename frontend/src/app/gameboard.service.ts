import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GameBoard} from "./gameBoard";

@Injectable({ providedIn: 'root' })
export class GameboardService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  public startGame(gameBoard: GameBoard): Observable<GameBoard> {
    return this.http.put<GameBoard>(`${this.apiUrl}/startGame`, gameBoard)
  }

  public getGameBoardById(gameBoardId: GameBoard): Observable<GameBoard[]> {
    return this.http.get<GameBoard[]>(`${this.apiUrl}/getGameById/${gameBoardId}`);
  }

  public getAllGameBoards(): Observable<GameBoard[]> {
    return this.http.get<GameBoard[]>(`${this.apiUrl}/getAllGames`);
  }
}
