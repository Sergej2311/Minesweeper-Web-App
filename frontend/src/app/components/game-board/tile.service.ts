import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tile} from "./minesweeper";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class TileService {
  private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  public getTiles(): Observable<Tile[]> {
    return this.http.get<Tile[]>(`${this.apiUrl}/tiles/all`)
  }

}
