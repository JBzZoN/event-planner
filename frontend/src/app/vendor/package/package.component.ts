import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { VendorPackage } from '../models/vendor-package';
import { VendorService } from '../vendor.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-package',
  templateUrl: './package.component.html',
  styleUrls: ['./package.component.css']
})
export class PackageComponent implements OnInit {
  packageData!: VendorPackage[];

  deletePopup: boolean = true;

  selectedId!: any;

  constructor(private vendorService: VendorService, private toaster: ToastrService, private router: Router) {}

  ngOnInit(): void {
      this.vendorService.getPackageDetail(localStorage.getItem("vendor")).subscribe((res: VendorPackage[]) => {
        this.packageData = res;
      });
  }

  newPackage() {
    this.router.navigate(["vendor", "package", "new"])
  }

  deletePackage(id: any) {
    this.selectedId = id;
    this.deletePopup = false;
  }

  editPackage(id: any) {
    this.selectedId = id;
  }

  cancelDeletionP() {
    this.deletePopup = true;
  }

  doDeletionP() {
    this.vendorService.deletePackage(this.selectedId).subscribe();

    this.deletePopup = true;
  }
}
