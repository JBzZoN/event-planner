import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VendorManagementResponse } from '../models/vendor-management-response';

@Injectable()
export class VendorManagementService {

  private baseUrl: string = 'http://localhost:8080/admin/'

  constructor(private client: HttpClient) { }
  getVendorManagementPage() : Observable<VendorManagementResponse>{
    return this.client.get<VendorManagementResponse>(this.baseUrl+"management")
  }
}
 