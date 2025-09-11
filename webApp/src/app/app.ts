import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WebShowToast } from 'compose-toast';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('webApp');
    private toast = new WebShowToast();
  showToast() {
this.toast.show(
      "Hello from Angular!",
      "#28a745",  // background color
      "#ffffff",  // text color
      "top-right" // position (optional)
    );
    }
}

