import { Component, OnInit } from '@angular/core';
import { Vendor } from '../../models/vendor';
// import { VendorService } from 'src/app/vendor/vendor.service';
import { VendorVerificationService } from '../../service/vendor-verification.service';
import { VerificationStatus } from '../../models/verification-status';
import { UpdateStatus } from '../../models/update-status';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-vendor-verification',
  templateUrl: './vendor-verification.component.html',
  styleUrls: ['./vendor-verification.component.css']
})
export class VendorVerificationComponent implements OnInit{
  isUpdating : boolean = false;
  updatingStatus : VerificationStatus | null = null;
  vendor! : Vendor[];
  application : Vendor | null = null;
  remarks!: string;
  verificationStatus = VerificationStatus
  constructor(private service : VendorVerificationService
) {

  }
  loadUnverifiedVendors(){
    this.service.getUnverifiedVendors().subscribe(response =>{
      this.vendor = response
    });
  }
  ngOnInit(): void {
    this.loadUnverifiedVendors();
  }
  reviewApplication(vendor : Vendor){
    this.application = vendor;
  }

  updateApplicationStatus(status : VerificationStatus){
    this.updatingStatus = status;
    if(this.isUpdating) return;
    this.isUpdating = true;
    const request : UpdateStatus = {
      verificationId : this.application!.verificationId,
      status : status,
      remarks : this.remarks
    }
    this.service.updateStatus(request).pipe(
      switchMap(() => this.service.getUnverifiedVendors())
    ).subscribe(vendors => {
      this.vendor = vendors;
      this.application = null;
      this.remarks = '';
      this.isUpdating = false;
    });
    
  }
}
