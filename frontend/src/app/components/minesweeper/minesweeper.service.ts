import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Minesweeper} from "./minesweeper";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class MinesweeperService {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  public startMinesweeper(): Observable<Minesweeper> {
    return this.http.get<Minesweeper>(`${this.apiUrl}/createGame`);
  }

  public getMinesweeper(): Observable<Minesweeper> {
    return this.http.get<Minesweeper>(`${this.apiUrl}/minesweeper/1`);
  }

  public leftClickTile(tileId: number, gameboard: Minesweeper): Observable<Minesweeper> {
    return this.http.post<Minesweeper>(`${this.apiUrl}/tiles/left-click/${tileId}`, gameboard);
  }
}
