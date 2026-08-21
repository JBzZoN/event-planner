import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { VendorDetails } from '../models/vendor.interface';
import { VendorService } from 'src/app/vendor/vendor.service';
import { AppService } from 'src/app/app.service';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-getting-started',
  templateUrl: './getting-started.component.html',
  styleUrls: ['./getting-started.component.css']  // Changed to .css
})
export class GettingStartedComponent {
  isVendorHovered = false;
  isUserHovered = false;

  vendorSelected = false;
  userSelected = false;

  vendorDetails: VendorDetails = {
    organisationName: '',
    phone: '',
    officeAddress: '',
    name: '',
    personalAddress: ''
  };

  constructor(private router: Router, private vendorService : VendorService, private appService: AppService, private oidcSecurityService: OidcSecurityService) {}

  selectVendor(): void {
    this.vendorSelected = true;
    console.log('Vendor selected');
  }

  selectUser(): void {
    this.userSelected = true;
    console.log('User selected');
  }

  cancel(): void {
    this.vendorSelected = false;
    this.userSelected = false;
  }

  onVendorSubmit(): void {
    this.vendorService.registerVendor(this.vendorDetails).subscribe(() => {
      this.appService.gettingStarted.set(false);
      this.oidcSecurityService.forceRefreshSession().subscribe(result => {
        this.router.navigate(['vendor', 'profile'])
      });
    });
  }
}