import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_model/product.model';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private httpClient: HttpClient) {}

  public addProduct(product: Product) {
    return this.httpClient.post<Product>(
      'http://localhost:9090/api/product/add',
      product
    );
  }

  public getAllProducts() {
    return this.httpClient.get<Product[]>(
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
}
