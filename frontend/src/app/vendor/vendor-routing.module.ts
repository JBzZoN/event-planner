import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { PackageComponent } from './package/package.component';

const routes: Routes = [
  {path:"", component: HomeComponent, children: [
    {path: "profile", component: ProfileComponent},
    {path: "package", component: PackageComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VendorRoutingModule { }
