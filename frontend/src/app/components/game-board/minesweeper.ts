export interface Gameboard {
  id: number;
  numRows: number;
  numCols: number;
  tilesSum: number;
  mineCount: number;
  tilesClicked: number
  gameOver: boolean;
}

export interface Tile {
  id: number;
  gameBoard: Gameboard;
  tileRow: number;
  tileColumn: number;
  isMine: boolean;
  tileText: string;
}
