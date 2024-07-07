import {Component} from '@angular/core';
import {MinesweeperComponent} from "./components/minesweeper/minesweeper.component";
import {HeaderComponent} from "./components/header/header.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    MinesweeperComponent,
    HeaderComponent
  ],
  standalone: true
})
export class AppComponent {
}
