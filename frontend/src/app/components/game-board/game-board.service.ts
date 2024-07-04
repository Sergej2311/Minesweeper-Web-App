import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Gameboard} from "./minesweeper";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class GameBoardService {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  public startGame(): Observable<Gameboard> {
    return this.http.get<Gameboard>(`${this.apiUrl}/startGame`)
  }

  public getGameBoards(): Observable<Gameboard[]> {
    return this.http.get<Gameboard[]>(`${this.apiUrl}/getAllGames`);
  }


}
