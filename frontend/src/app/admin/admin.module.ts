import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { HomeComponent } from './home/home.component';
import { DragonComponent } from './dragon/dragon.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DashboardService } from './service/dashboard.service';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    HomeComponent,
    DragonComponent,
    SidebarComponent,
    DashboardComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    AdminRoutingModule
  ],
  providers: [
    DashboardService
  ]
})
export class AdminModule { }
