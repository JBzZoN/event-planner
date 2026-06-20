import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VendorRoutingModule } from './vendor-routing.module';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { PackageComponent } from './package/package.component';
import {HttpClientModule} from '@angular/common/http'
import { VendorService } from './vendor.service';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    HomeComponent,
    ProfileComponent,
    PackageComponent
  ],
  imports: [
    CommonModule,
    VendorRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers:[
    VendorService
  ]
})
export class VendorModule { }
