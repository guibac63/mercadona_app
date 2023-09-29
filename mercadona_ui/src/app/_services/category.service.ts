import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../_model/category.model';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) {}

  public addCategory(category: Category) {
    return this.httpClient.post<Category>("http://localhost:9090/api/category/add", category);
  }

  public getAllCategories() {
    return this.httpClient.get<Category[]>("http://localhost:9090/api/category/getAll")
  }

  public deleteCategory(categoryId: number) {
    return this.httpClient.delete('http://localhost:9090/api/category/delete/' + categoryId);
  }

  public getCategoryById(categoryId: any){
    if(categoryId){
      return this.httpClient.get(
        'http://localhost:9090/api/category/get/' + categoryId
      );
    }else{
     return of([]);
    }
  }
}
