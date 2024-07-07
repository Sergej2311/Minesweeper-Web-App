import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Gameboard} from "./minesweeper";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class MinesweeperService {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  public createGame(): Observable<Gameboard> {
    return this.http.get<Gameboard>(`${this.apiUrl}/createGame`);
  }

  public getGameBoards(): Observable<Gameboard[]> {
    return this.http.get<Gameboard[]>(`${this.apiUrl}/getAllGames`);
  }

  public leftClickTile(tileId: number, gameboard: Gameboard): Observable<Gameboard> {
    return this.http.post<Gameboard>(`${this.apiUrl}/tiles/left-click/${tileId}`, gameboard);
  }
}
