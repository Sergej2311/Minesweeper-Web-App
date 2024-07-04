import {Component} from '@angular/core';
import {GameBoardComponent} from "./components/game-board/game-board.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    GameBoardComponent
  ],
  standalone: true
})
export class AppComponent {
}
