import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { HomeComponent } from './home/home.component';
import { DragonComponent } from './dragon/dragon.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DashboardService } from './service/dashboard.service';
import { HttpClientModule } from '@angular/common/http';
import { VendorVerificationService } from './service/vendor-verification.service';
import { VendorVerificationComponent } from './components/vendor-verification/vendor-verification.component';
import { FormsModule } from '@angular/forms';
import { VendorManagementComponent } from './components/vendor-management/vendor-management.component';
import { VendorManagementService } from './service/vendor-management.service';


@NgModule({
  declarations: [
    HomeComponent,
    DragonComponent,
    SidebarComponent,
    DashboardComponent,
    VendorVerificationComponent,
    VendorManagementComponent,
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    AdminRoutingModule,
    FormsModule,
  ],
  providers: [
    DashboardService,
    VendorVerificationService,
    VendorManagementService
  ]
})
export class AdminModule { }
