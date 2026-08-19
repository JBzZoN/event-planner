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

    // CHANGE THIS
    localStorage.clear()
    localStorage.setItem("vendor", "1");
    
    // THIS IS ONLY DURING DEVELOPMENT
    this.service.getVendorDetail(localStorage.getItem("vendor")).subscribe((value: Vendor) => {
      this.vendorName = value.orgName;
    })  
  }
  
}
