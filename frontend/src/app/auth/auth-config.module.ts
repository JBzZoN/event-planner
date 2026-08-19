import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
              authority: 'http://localhost:8081/realms/master',
              redirectUrl: window.location.origin,
              postLogoutRedirectUri: window.location.origin,
              clientId: 'angular-frontend',
              scope: 'openid profile offline_access',
              responseType: 'code',
              silentRenew: true,
              useRefreshToken: true,
              renewTimeBeforeTokenExpiresInSeconds: 30,
              secureRoutes: ["http://localhost:8080/"]
          }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
