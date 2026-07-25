import { Component, OnInit } from '@angular/core';
import { VendorManagementService } from '../../service/vendor-management.service';
import { VendorManagementResponse } from '../../models/vendor-management-response';
import { PackageDto } from '../../models/package-dto';
import { VendorSummary } from '../../models/vendor-summary';
import { PlannerStatus } from '../../models/planner-status';

@Component({
  selector: 'app-vendor-management',
  templateUrl: './vendor-management.component.html',
  styleUrls: ['./vendor-management.component.css']
})
export class VendorManagementComponent implements OnInit{ 
  vendorDetails: PackageDto[] | null = null;
  vendorPage! : VendorManagementResponse;
  selectedVendor! : VendorSummary;
  plannerStatus = PlannerStatus;
  
  constructor(private service : VendorManagementService){}
  ngOnInit(): void {
    this.service.getVendorManagementPage().subscribe(response => {
      this.vendorPage = response
    })
  }
  getVendorDetails(vendor : VendorSummary): void{
    this.selectedVendor = vendor
    console.log("Date",vendor.suspendUntil);
    this.service.getEachVendorDetail(vendor.orgId).subscribe(response => {
      this.vendorDetails = response;
    })
  }
}
