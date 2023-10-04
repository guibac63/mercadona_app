import {
  ActivatedRouteSnapshot,
  ResolveFn,
  RouterStateSnapshot,
} from '@angular/router';
import { inject } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs';
import { ProductService } from '../_services/product-service';

export const RouteProductResolver: ResolveFn<any> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  productService: ProductService = inject(ProductService)
): Observable<{}> =>
  productService.getProductById(route.paramMap.get('id')).pipe(
    catchError((err) => {
      return of('No data' + err);
    })
  );
