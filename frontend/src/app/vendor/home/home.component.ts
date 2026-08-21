import { Component, ElementRef, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Vendor } from '../models/vendor';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  vendorName: String="";

  constructor(public service: VendorService) {
  }

  ngOnInit(): void {
    this.service.getVendorDetail().subscribe((value: Vendor) => {
      this.vendorName = value.orgName;
    })  
  }
  
}
