import { Component, OnInit } from '@angular/core';
import { VendorManagementService } from '../../service/vendor-management.service';
import { VendorManagementResponse } from '../../models/vendor-management-response';

@Component({
  selector: 'app-vendor-management',
  templateUrl: './vendor-management.component.html',
  styleUrls: ['./vendor-management.component.css']
})
export class VendorManagementComponent implements OnInit{

  vendorPage! : VendorManagementResponse
  constructor(private service : VendorManagementService){}
  ngOnInit(): void {
    this.service.getVendorManagementPage().subscribe(response => {
      this.vendorPage = response
    })
  }

}
