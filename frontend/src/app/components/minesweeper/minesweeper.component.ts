import {Component, Inject, OnInit} from '@angular/core';
import {Minesweeper, Tile} from "./minesweeper";
import {MinesweeperService} from "./minesweeper.service";
import {DOCUMENT, NgFor, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'minesweeper',
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
  public minesweeper: Minesweeper;
  public gameWon: boolean = false;
  public gameOver: boolean = false;
  public tiles: Tile[] = [];

  constructor(private minesweeperService: MinesweeperService, @Inject(DOCUMENT) private document: Document) { }

  ngOnInit() {
    this.initializeMinesweeper();
    this.document.addEventListener('contextmenu', event => event.preventDefault());
  }

  public loadData() {
    this.getMinesweeper();
    this.getTiles();
  }

  public getMinesweeper(): void {
    this.minesweeperService.getMinesweeper().subscribe(
      (response: Minesweeper) => {
        this.minesweeper = response;
        this.gameWon = this.minesweeper.gameWon;
        this.gameOver = this.minesweeper.gameOver;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getTiles(): void {
    this.minesweeperService.getTiles().subscribe(
      (response: Tile[]) => {
        this.tiles = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public initializeMinesweeper(): void {
    this.minesweeperService.initializeMinesweeper().subscribe(
      () => {
        this.loadData();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public leftClickTile(tileId: number, minesweeper: Minesweeper): void {
    this.minesweeperService.leftClickTile(tileId, minesweeper).subscribe(
      () => {
        this.loadData();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public rightClickTile(tileId: number): void {
    this.minesweeperService.rightClickTile(tileId).subscribe(
      () => {
        this.loadData();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public solve(): void {
    this.minesweeperService.solve().subscribe(
      () => {
        this.loadData();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
