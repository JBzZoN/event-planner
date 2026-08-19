import { Component } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(private router: Router) {}

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
}