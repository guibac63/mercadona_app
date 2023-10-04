import { Component } from '@angular/core';
import { Product } from '../_model/product.model';
import { CategoryService } from '../_services/category.service';
import { ProductService } from '../_services/product-service';
import { ActivatedRoute, Router } from '@angular/router';
import { PromotionService } from '../_services/promotion.service';
import { Category } from '../_model/category.model';
import { Promotion } from '../_model/promotion.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-product-admin-form',
  templateUrl: './product-admin-form.component.html',
  styleUrls: ['./product-admin-form.component.css'],
})
export class ProductAdminFormComponent {
  product: Product = {
    id: 0,
    productLibelle: '',
    productDescription: '',
    productPrice: 0,
    productDiscountedPrice: 0,
    category: null,
    promotion: null,
  };

  categories: Category[];

  promotions: Promotion[];

  selectedCategory: Category[];

  selectedPromotion: Promotion[];

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private promotionService: PromotionService,
    private router: Router,
    private actRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    if (this.actRoute.snapshot.params.id != undefined) {
      this.actRoute.data.subscribe((data) => {
        this.product = data.routeProductResolver;
      });
    }

    this.getAllCategories();
    this.getAllPromotions();
  }

  compareWithCategory(object1: Category, object2: Category) {
    return object1 && object2 && object1.categoryName === object2.categoryName;
  }

  compareWithPromotion(object1: Promotion, object2: Promotion) {
    return object1 && object2 && object1.promotionName === object2.promotionName;
  }

  public getAllCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: (response: any) => {
        this.categories = response;
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  public getAllPromotions() {
    this.promotionService.getAllPromotions().subscribe({
      next: (response: any) => {
        this.promotions = response;
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  addProduct(productForm: NgForm) {
    console.log(productForm.value);

    this.productService.addProduct(this.product).subscribe({
      next: (response: Product) => {
        // console.log(response);
        this.router.navigate(['/admin/product_admin']);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
