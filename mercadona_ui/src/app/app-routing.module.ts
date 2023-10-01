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
import { RouteCategoryResolver } from './_resolvers/route.category-resolver';
import { PromotionAdminComponent } from './promotion-admin/promotion-admin.component';
import { PromotionAdminFormComponent } from './promotion-admin-form/promotion-admin-form.component';
import { RoutePromotionResolver } from './_resolvers/route.promotion-resolver';

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
    resolve: { routeCategoryResolver: RouteCategoryResolver },
  },
  {
    path: 'admin/promotion_admin',
    component: PromotionAdminComponent,
    canActivate: [authGuard],
  },
  {
    path: 'admin/promotion_admin/form',
    component: PromotionAdminFormComponent,
    canActivate: [authGuard],
    resolve: { routeCategoryResolver: RoutePromotionResolver },
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
