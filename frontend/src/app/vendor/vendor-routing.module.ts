import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { PackageComponent } from './package/package.component';
import { NewPackageComponent } from './new-package/new-package.component';

const routes: Routes = [
  {path:"", component: HomeComponent, children: [
    {path: "profile", component: ProfileComponent},
    {path: "package", component: PackageComponent},
    {path: "package/new", component: NewPackageComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VendorRoutingModule { }
