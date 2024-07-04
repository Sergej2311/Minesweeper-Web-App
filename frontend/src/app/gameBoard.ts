export interface GameBoard {
  id: number;
  numRows: number;
  numCols: number;
  tilesSum: number;
  mineCount: number;
  tilesClicked: number
  gameOver: boolean;
}
