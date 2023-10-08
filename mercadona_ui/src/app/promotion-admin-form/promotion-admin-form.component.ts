import { Component } from '@angular/core';
import { Promotion } from '../_model/promotion.model';
import { PromotionService } from '../_services/promotion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { DateRangeValidatorDirective } from '../_directives/date-range-validator.directive';

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
    beginningDate: this.currentDate,
    endingDate: this.currentDate,
    promotionPercentage: null,
  };
  alreadyExistPromotionError: boolean = false;

  constructor(
    private promotionService: PromotionService,
    private router: Router,
    private actRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    if (this.actRoute.snapshot.params.id != undefined) {
      this.actRoute.data.subscribe((data) => {
        this.promotion = data.routePromotionResolver;
      });
    }
  }

  addPromotion(promotionForm: NgForm) {
    if(promotionForm.valid){
      this.promotionService.addPromotion(this.promotion).subscribe({
        next: (response: any) => {
          if (response.status == 200) {
            console.log(response);
            this.router.navigate(['/admin/promotion_admin']);
          } else {
            if (response.message.includes('ConstraintViolationException')) {
              this.alreadyExistPromotionError = true;
            }
          }
        },
        error: (error) => {
          console.log(error);
        },
      });
    }
  }

  resetNoExistErrors() {
    this.alreadyExistPromotionError = false;
  }
}
