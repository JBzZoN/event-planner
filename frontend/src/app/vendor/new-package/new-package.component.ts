import { Component } from '@angular/core';
import { VendorPackage } from '../models/vendor-package';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { VendorService } from './../vendor.service';

@Component({
  selector: 'app-new-package',
  templateUrl: './new-package.component.html',
  styleUrls: ['./new-package.component.css']
})
export class NewPackageComponent {

  constructor(private toaster: ToastrService, private router: Router, private vendorService: VendorService) {}

  newPackageToPost: VendorPackage = {
    packageId: null,
    packageName: '',
    packagePrice: 0,
    packageGroup: [{
      groupId: null,
      groupName: '',
      packageGroupItem:[{
        itemId: null,
        itemName: '',
        itemPrice: 0
      }]
    }]
  };
  
  addNewPackageGroup() {  
    this.newPackageToPost.packageGroup.push({
      groupId: null,
      groupName: '',
      packageGroupItem:[{
        itemId: null,
        itemName: '',
        itemPrice: 0
      }]
    })
  }

  addItem(id: number) {
    this.newPackageToPost.packageGroup[id].packageGroupItem.push({
      itemId: null,
      itemPrice: 0,
      itemName: ''
    })
  }

  savePackage() {
    this.vendorService.createPackage(localStorage.getItem("vendor"), this.newPackageToPost).subscribe((response) => {
      this.router.navigate(["vendor", "package"])
    })
  }

  removeGroup(id: number) {

    if(this.newPackageToPost.packageGroup.length == 1) {
      this.toaster.error("Atleast one group!")
      return;
    }

    this.newPackageToPost.packageGroup.splice(id, 1);
    this.updateTotalPrice();
  }

  removeItem(pid: number, iid: number) {
    
    if(this.newPackageToPost.packageGroup[pid].packageGroupItem.length == 1) {
      this.toaster.error("Atleast one item!")
      return;
    }

    this.newPackageToPost.packageGroup[pid].packageGroupItem.splice(iid, 1);
    this.updateTotalPrice();
  }

  updateTotalPrice() {
    let price = 0;

    for(let group of this.newPackageToPost.packageGroup) {
      for(let item of group.packageGroupItem) {
        price += item.itemPrice;
      }
    }

    this.newPackageToPost.packagePrice = price;
  }

}
