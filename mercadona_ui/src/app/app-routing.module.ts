import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { authGuard } from './_auth/auth.guard';
import { CategoryAdminComponent } from './category-admin/category-admin.component';
import { ProductAdminComponent } from './product-admin/product-admin.component';
import { CategoryAdminFormComponent } from './category-admin-form/category-admin-form.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [authGuard],
  },
  { path: 'login', component: LoginComponent },
  { path: 'forbidden', component: ForbiddenComponent },
  {
    path: 'admin/category_admin',
    component: CategoryAdminComponent,
    canActivate: [authGuard],
  },
  {
    path: 'admin/category_admin/form',
    component: CategoryAdminFormComponent,
    canActivate: [authGuard],
  },
  {
    path: 'admin/product_admin',
    component: ProductAdminComponent,
    canActivate: [authGuard],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
