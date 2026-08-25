import { Component, ElementRef, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Vendor } from '../models/vendor';
import { Router } from '@angular/router';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  vendorName: String="";

  constructor(public service: VendorService, private router: Router, public oidcSecurityService: OidcSecurityService) {
  }

  logout() {
    this.oidcSecurityService.logoff().subscribe({
      next: () => {
        this.router.navigate(['/']);
      }
    });
  }

  ngOnInit(): void {
    this.service.getVendorDetail().subscribe((value: Vendor) => {
      this.vendorName = value.orgName;
    })  
  }
  
}
