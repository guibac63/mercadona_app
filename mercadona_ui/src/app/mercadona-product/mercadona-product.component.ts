import { Component, Directive, ViewChild } from '@angular/core';
import { Category } from '../_model/category.model';
import { CategoryService } from '../_services/category.service';
import { ProductService } from '../_services/product-service';
import { Product } from '../_model/product.model';
import { ActivatedRoute } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';

@Component({
  selector: 'app-mercadona-product',
  templateUrl: './mercadona-product.component.html',
  styleUrls: ['./mercadona-product.component.css'],
})
export class MercadonaProductComponent {
  products: any[];

  product: Product = {
    id: 0,
    productLibelle: '',
    productDescription: '',
    productPrice: 0,
    productDiscountedPrice: 0,
    category: null,
    promotion: null,
    image: null,
  };

  categories: Category[];

  cols: number;

  gridByBreakpoint = {
    xl: 4,
    lg: 4,
    md: 3,
    sm: 2,
    xs: 1,
  };

  constructor(
    private categoryService: CategoryService,
    private productService: ProductService,
    private actRoute: ActivatedRoute,
    private breakpointObserver: BreakpointObserver
  ) {
    this.breakpointObserver
      .observe([
        Breakpoints.XSmall,
        Breakpoints.Small,
        Breakpoints.Medium,
        Breakpoints.Large,
        Breakpoints.XLarge,
      ])
      .subscribe((result) => {
        if (result.matches) {
          if (result.breakpoints[Breakpoints.XSmall]) {
            this.cols = this.gridByBreakpoint.xs;
          }
          if (result.breakpoints[Breakpoints.Small]) {
            this.cols = this.gridByBreakpoint.sm;
          }
          if (result.breakpoints[Breakpoints.Medium]) {
            this.cols = this.gridByBreakpoint.md;
          }
          if (result.breakpoints[Breakpoints.Large]) {
            this.cols = this.gridByBreakpoint.lg;
          }
          if (result.breakpoints[Breakpoints.XLarge]) {
            this.cols = this.gridByBreakpoint.xl;
          }
        }
      });
  }

  ngOnInit(): void {
    this.actRoute.data.subscribe((data) => {
      this.products = data.routeAllProductsResolver;
      console.log(data.routeAllProductsResolver);
    });
    this.getAllCategories();
  }

  compareWithCategory(object1: Category, object2: Category) {
    return object1 && object2 && object1.categoryName === object2.categoryName;
  }

  public getAllCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: (response: any) => {
        this.categories = response.data;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
