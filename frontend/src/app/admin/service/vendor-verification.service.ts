import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dashboard } from '../models/dashboard';
import { Vendor } from '../models/vendor';
import { UpdateStatus } from '../models/update-status';

@Injectable()
export class VendorVerificationService {
  private baseUrl: string = 'http://localhost:8080/admin/'

  constructor(private client: HttpClient) { }
  getUnverifiedVendors() : Observable<Vendor[]>{
    return this.client.get<Vendor[]>(this.baseUrl+"verification")
  }
  
  updateStatus(updateStatus: UpdateStatus): Observable<any> {
      return this.client.patch<any>(
          this.baseUrl + "verification",
          updateStatus
      );
  }
}
