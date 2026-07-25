import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Vendor } from './models/vendor';
import { Observable } from 'rxjs';
import { UserDetail } from './models/user-detail';
import { VendorPackage } from './models/vendor-package';

@Injectable()
export class VendorService {

  baseUrl: String = "http://localhost:8080/vendor"

  constructor(private client: HttpClient) { }

  getVendorDetail(id: (string|null)) : Observable<Vendor> {
    return this.client.get<Vendor>(this.baseUrl + "/details/" + id);
  }

  getPackageDetail(id: (string|null)) : Observable<VendorPackage[]> {
    return this.client.get<VendorPackage[]>(this.baseUrl + "/package/" + id);
  }

  getContactsOfOrg(id: (string|null)) : Observable<UserDetail[]> {
    return this.client.get<UserDetail[]>(this.baseUrl + "/contacts/" + id);
  }

  updateVendorDetail(id: (string|null), vendor: Vendor) {
    this.client.patch(this.baseUrl + "/details/" + id, vendor).subscribe();
  }

  createPackage(id: (string|null), pkg: VendorPackage) {
    return this.client.post(this.baseUrl + "/package/" + id, pkg);
  }
}
