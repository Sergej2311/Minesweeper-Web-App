export interface Minesweeper {
  id: number;
  numRows: number;
  numCols: number;
  tilesSum: number;
  mineCount: number;
  tilesClicked: number;
  gameOver: boolean;
  gameWon: boolean;
}

export interface Tile {
  id: number;
  gameBoard: Minesweeper;
  tileRow: number;
  tileColumn: number;
  mine: boolean;
  tileText: string;
}
