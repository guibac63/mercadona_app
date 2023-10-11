import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Promotion } from '../_model/promotion.model';
import { of } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class PromotionService {
  constructor(private httpClient: HttpClient) {}

  public addPromotion(promotion: Promotion) {
    return this.httpClient.post<Promotion>(
      'http://localhost:9090/api/promotion/add',
      promotion
    );
  }

  public getAllPromotions() {
    return this.httpClient.get<Promotion[]>(
      'http://localhost:9090/api/promotion/getAll'
    );
  }

  public deletePromotion(promotionId: number) {
    return this.httpClient.delete(
      'http://localhost:9090/api/promotion/delete/' + promotionId
    );
  }

  public getPromotionById(promotionId: any) {
    if (promotionId) {
      return this.httpClient.get(
        'http://localhost:9090/api/promotion/get/' + promotionId
      );
    } else {
      return of([]);
    }
  }

  
}
