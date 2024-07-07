import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Minesweeper, Tile} from "./minesweeper";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class MinesweeperService {
  private backendUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  public initializeMinesweeper(): Observable<Minesweeper> {
    return this.http.get<Minesweeper>(`${this.backendUrl}/minesweeper/initialize`);
  }

  public getMinesweeper(): Observable<Minesweeper> {
    return this.http.get<Minesweeper>(`${this.backendUrl}/minesweeper/1`);
  }

  public getTiles(): Observable<Tile[]> {
    return this.http.get<Tile[]>(`${this.backendUrl}/tiles/all`)
  }

  public leftClickTile(tileId: number, minesweeper: Minesweeper): Observable<Minesweeper> {
    return this.http.post<Minesweeper>(`${this.backendUrl}/tiles/left-click/${tileId}`, minesweeper);
  }

  public rightClickTile(tileId: number, minesweeper: Minesweeper): Observable<Minesweeper> {
    return this.http.post<Minesweeper>(`${this.backendUrl}/tiles/right-click/${tileId}`, minesweeper);
  }

  public solve(minesweeper: Minesweeper): Observable<Minesweeper> {
    return this.http.post<Minesweeper>(`${this.backendUrl}/tiles/solve`, minesweeper);
  }
}
