import {Component} from '@angular/core';
import {MinesweeperComponent} from "./components/minesweeper/minesweeper.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    MinesweeperComponent
  ],
  standalone: true
})
export class AppComponent {
}
