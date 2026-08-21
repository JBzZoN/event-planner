import { inject, NgModule, OnInit } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthConfigModule } from './auth/auth-config.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from 'angular-auth-oidc-client';
import { GettingStartedComponent } from './shared/getting-started/getting-started.component';
import { VendorService } from './vendor/vendor.service';
import { AppService } from './app.service';

@NgModule({
  declarations: [
    AppComponent,
    GettingStartedComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    ToastrModule.forRoot({
      positionClass: "toast-top-right"
    }),
    AuthConfigModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    VendorService,
    AppService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
