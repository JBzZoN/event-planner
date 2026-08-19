import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: "admin", loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)},
  {path: "vendor", loadChildren: () => import('./vendor/vendor.module').then(m => m.VendorModule)},
  {path: "user", loadChildren: () => import('./user/user.module').then(m => m.UserModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
