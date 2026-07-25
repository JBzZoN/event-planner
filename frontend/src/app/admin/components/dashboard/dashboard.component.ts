import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../service/dashboard.service';
import { Dashboard } from '../../models/dashboard';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  dashboard! : Dashboard
  constructor(private service: DashboardService){

  }
  ngOnInit(): void {
    this.service.getDashboardStats().subscribe(response => {
      this.dashboard = response
    });
  }
}
