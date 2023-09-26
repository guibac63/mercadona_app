import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../_model/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) {}

  public addCategory(category: Category) {
    return this.httpClient.post<Category>("http://localhost:9090/api/category/add", category);
  }
}
