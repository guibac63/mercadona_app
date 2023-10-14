import { Component, Directive, ViewChild } from '@angular/core';
import { Category } from '../_model/category.model';
import { CategoryService } from '../_services/category.service';
import { Product } from '../_model/product.model';
import { ActivatedRoute } from '@angular/router';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { MercadonaProduct } from '../_model/mercadonaProduct.model';
import { MatPaginator } from '@angular/material/paginator';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-mercadona-product',
  templateUrl: './mercadona-product.component.html',
  styleUrls: ['./mercadona-product.component.css'],
})
export class MercadonaProductComponent {
  @ViewChild('paginator') paginator: MatPaginator;

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

  productsFiltered: MercadonaProduct[];

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
      this.productsFiltered = this.checkPromotionValidity(this.products);
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

  public filterProducts(evt) {
    if (evt.value != 0) {
      this.productsFiltered = this.checkPromotionValidity(
        this.products.filter((product) => {
          return product.category.id === evt.value.id;
        })
      );
    } else {
      this.productsFiltered = this.checkPromotionValidity(this.products);
    }
  }

  public checkPromotionValidity(products) {
    let productsFiltered = products;

    productsFiltered.forEach((product) => {
      let now = Date.now();
      if (
        product.promotion &&
        Date.parse(product.promotion.beginningDate) <= now &&
        Date.parse(product.promotion.endingDate) >= now
      ) {
        product.isPromotionValid = true;
      } else {
        product.isPromotionValid = false;
      }
    });

    return productsFiltered;
  }
}