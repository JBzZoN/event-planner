import { Component, inject, OnInit, signal } from '@angular/core';
import { Router } from '@angular/router';
import { LoginResponse, OidcSecurityService } from 'angular-auth-oidc-client';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';

  constructor(public oidcSecurityService: OidcSecurityService, public router: Router, public appService: AppService) {}

  ngOnInit() {
    this.oidcSecurityService
      .checkAuth()
      .subscribe((loginResponse: LoginResponse) => {
        const { isAuthenticated, userData, accessToken, idToken, configId } =
          loginResponse;

        if(!isAuthenticated) this.login();
        else {
          this.oidcSecurityService
          .getPayloadFromAccessToken()
          .subscribe(payload => {

            const roles = payload['realm_access']?.['roles'] ?? [];

            if (roles.includes('VENDOR')) {
              this.router.navigate(['vendor', 'profile']);
            } else if(roles.includes('ADMIN')) {
              this.router.navigate(['admin', 'dashboard']);
            } else if(roles.includes('USER')) {
              this.router.navigate(['user', 'home']);
            } else {
              this.appService.gettingStarted.set(true);
            }
          });
        }
        
      });
  }

  login() {
    this.oidcSecurityService.authorize();
  }

  logout() {
    this.oidcSecurityService
      .logoff()
      .subscribe((result) => console.log(result));
  }
}
