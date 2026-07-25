import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dashboard } from '../models/dashboard';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class DashboardService {
  
  private baseUrl: string = 'http://localhost:8080/admin/'

  constructor(private client: HttpClient) { }
  getDashboardStats() : Observable<Dashboard>{
    return this.client.get<Dashboard>(this.baseUrl+"dashboard");
  }
} 
