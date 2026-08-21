import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Vendor } from './models/vendor';
import { Observable } from 'rxjs';
import { UserDetail } from './models/user-detail';
import { VendorPackage } from './models/vendor-package';
import { VendorDetails } from '../shared/models/vendor.interface';

@Injectable()
export class VendorService {

  baseUrl: String = "http://localhost:8080/vendor"

  constructor(private client: HttpClient) { }

  deletePackage(id: (string|null)) {
    return this.client.delete(this.baseUrl + "/package/" + id);
  }

  getVendorDetail() : Observable<Vendor> {
    return this.client.get<Vendor>(this.baseUrl + "/details");
  }

  getPackageDetail() : Observable<VendorPackage[]> {
    return this.client.get<VendorPackage[]>(this.baseUrl + "/package");
  }

  getContactsOfOrg(id: (string|null)) : Observable<UserDetail[]> {
    return this.client.get<UserDetail[]>(this.baseUrl + "/contacts/" + id);
  }

  updateVendorDetail(id: (string|null), vendor: Vendor) {
    this.client.patch(this.baseUrl + "/details/" + id, vendor).subscribe();
  }

  createPackage(pkg: VendorPackage) {
    return this.client.post(this.baseUrl + "/package", pkg);
  }

  registerVendor(vendorDetails: VendorDetails) {
    return this.client.post("http://localhost:8080/register/vendor", vendorDetails);
  }
}
