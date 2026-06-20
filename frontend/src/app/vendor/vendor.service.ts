import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Vendor } from './models/vendor';
import { Observable } from 'rxjs';

@Injectable()
export class VendorService {

  baseUrl: String = "http://localhost:8080/vendor"

  constructor(private client: HttpClient) { }

  getVendorDetail(id: (string|null)) : Observable<Vendor> {
    return this.client.get<Vendor>(this.baseUrl + "/details/" + id);
  }

  updateVendorDetail(id: (string|null), vendor: Vendor) {
    this.client.patch(this.baseUrl + "/details/" + id, vendor).subscribe();
  }
}
