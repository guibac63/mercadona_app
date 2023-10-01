import { Component } from '@angular/core';
import { Promotion } from '../_model/promotion.model';
import { PromotionService } from '../_services/promotion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-promotion-admin-form',
  templateUrl: './promotion-admin-form.component.html',
  styleUrls: ['./promotion-admin-form.component.css'],
})
export class PromotionAdminFormComponent {
  currentDate: any = new Date();
  promotion: Promotion = {
    id: 0,
    promotionName: '',
    beginningDate: null,
    endingDate: null,
    promotionPercentage: null,
  };

  constructor(
    private promotionService: PromotionService,
    private router: Router,
    private actRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    if (this.actRoute.snapshot.params.id != undefined) {
      this.actRoute.data.subscribe((data) => {
        this.promotion = data.routeCategoryResolver;
      });
    }
  }

  addPromotion(promotionForm: NgForm) {
    this.promotionService.addPromotion(this.promotion).subscribe({
      next: (response: Promotion) => {
        console.log(response);
        this.router.navigate(['/admin/promotion_admin']);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
