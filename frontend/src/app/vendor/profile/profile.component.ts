import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Router } from '@angular/router';
import { Vendor } from '../models/vendor';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private service: VendorService) {
  }

  readMode: boolean = true;
  vendor!: Vendor;

  buttonMessage!: String;
  statusMessage!: String;

  ngOnInit(): void {
    this.service.getVendorDetail(localStorage.getItem("vendor")).subscribe(response => {
      this.vendor = response;

      if (this.vendor) {
        switch (this.vendor.status) {
          case 'PENDING_VERIFICATION':
            this.buttonMessage = 'Verification in progress';
            this.statusMessage = "Verification in progress"
            break;

          case 'ACTIVE':
            this.buttonMessage = 'Account Active';
            this.statusMessage = "Active";
            break;

          case 'SUSPENDED':
            this.buttonMessage = 'Account Suspended';
            this.statusMessage = "Suspended";
            break;

          case 'INACTIVE':
            this.buttonMessage = 'Request for verification';
            this.statusMessage = "Inactive";
            break;
        }
      }
    });
  }


  editMajorDetails() {
    this.readMode = false;
  }

  updateMajorDetails() {
    this.readMode = true;
    this.service.updateVendorDetail(this.vendor.orgId.toString(), this.vendor);
  }

  cancelMajorDetails() {
    this.readMode = true;
    this.service.updateVendorDetail(this.vendor.orgId.toString(), this.vendor);
  }

}
