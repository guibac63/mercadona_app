import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product.model';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private httpClient: HttpClient) {}

  public addProduct(product: FormData) {
    return this.httpClient.post<Product>(
      'http://localhost:9090/api/product/add',
      product
    );
  }

  public getAllProducts() {
    return this.httpClient.get<any>(
      'http://localhost:9090/api/product/getAll'
    );
  }

  public deleteProduct(productId: number) {
    return this.httpClient.delete(
      'http://localhost:9090/api/product/delete/' + productId
    );
  }

  public getProductById(productId: any) {
    if (productId) {
      return this.httpClient.get(
        'http://localhost:9090/api/product/get/' + productId
      );
    } else {
      return of([]);
    }
  }

  public modifyProductDiscountedPrice(promotionId: any) {
    console.log("dans service!!")
    if (promotionId) {
      console.log('dans service avec ID!!');
      return this.httpClient.get(
        'http://localhost:9090/api/product/modifyProductDiscountedPrice/' +
          promotionId
      );
    } else {
       console.log('probleme!!');
      return of([]);
    }
  }
}
