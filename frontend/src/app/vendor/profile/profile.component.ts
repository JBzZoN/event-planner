import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Router } from '@angular/router';
import { Vendor } from '../models/vendor';
import { UserDetail } from '../models/user-detail';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private service: VendorService, private toast: ToastrService) {
  }

  readMode: boolean = true;
  vendor!: Vendor;
  userDetails!: UserDetail[];

  buttonMessage!: String;
  statusMessage!: String;
  showContactPopup: boolean = false;

  newContactDetail: UserDetail = {
    emailAddress: "",
    address: "",
    name:"",
    password:"",
    phone:"",
    userId: -1,
    username: "",
    userRole: "VENDOR"
  };

  ngOnInit(): void {
    this.updatePageDetails()
  }

  updatePageDetails() {
    this.service.getVendorDetail().subscribe(response => {
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

      this.service.getContactsOfOrg(String(this.vendor.orgId)).subscribe(response => {
        this.userDetails = response;
      });
    });
  }

  onClickVerification() {
    if(this.vendor.status == 'INACTIVE') {
      this.showVerificationPopup = true;
    }
  }


  editMajorDetails() {
    this.readMode = false;
  }

  updateMajorDetails() {
    this.readMode = true;
    this.service.updateVendorDetail(this.vendor.orgId.toString(), this.vendor);
  }

  addContacts() {
    this.showContactPopup = true;
  }

  cancelContact() {
    this.showContactPopup = false;
  }

  submitContact() {
    this.service.addContactToVendor(this.vendor.orgId.toString(), this.newContactDetail).subscribe(() => {
      this.showContactPopup = false;
      this.service.getContactsOfOrg(String(this.vendor.orgId)).subscribe(response => {
        this.userDetails = response;
      });
    });
  }

  cancelMajorDetails() {
    this.readMode = true;
    this.service.updateVendorDetail(this.vendor.orgId.toString(), this.vendor);
  }

  showVerificationPopup: boolean = false;

  gstCertificate!: File;

  onGstCertificateSelected(event: any) {
      this.gstCertificate = event.target.files[0];
  }

  submitVerification() {
    if(this.gstCertificate) {
      this.service.sendForVerification(this.gstCertificate, this.vendor.orgId).subscribe(() => {
        this.updatePageDetails()
        this.showVerificationPopup=false;
      });
    }else {
      this.toast.warning("No PDF selected")
    }
  }
}
