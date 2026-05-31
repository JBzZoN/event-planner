import { Component } from '@angular/core';

@Component({
  selector: 'app-dummy',
  templateUrl: './dummy.component.html',
  styleUrls: ['./dummy.component.css']
})
export class DummyComponent {
  count : number = 0;

  onClick(){
    this.count ++;
  }
}
