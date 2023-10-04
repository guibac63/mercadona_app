import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { ProductService } from '../_services/product-service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';


@Component({
  selector: 'app-show-product-details',
  templateUrl: './show-product-details.component.html',
  styleUrls: ['./show-product-details.component.css'],
})
export class ShowProductDetailsComponent {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  productDetails: any;
  displayedColumns: string[] = [
    'id',
    'productLibelle',
    'category',
    'productPrice',
    'promotion',
    'productDiscountedPrice',
    'edit',
  ];

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.getAllProducts();
  }

  public getAllProducts() {
    this.productService.getAllProducts().subscribe({
      next: (response: any) => {
        this.productDetails = new MatTableDataSource(response);
        this.productDetails.paginator = this.paginator;
        this.productDetails.sort = this.sort;
        const sortState: Sort = { active: 'id', direction: 'asc' };
        this.sort.active = sortState.active;
        this.sort.direction = sortState.direction;
        this.sort.sortChange.emit(sortState);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  deleteProduct(productId) {
    this.productService.deleteProduct(productId).subscribe({
      next: (response) => {
        console.log(response);
        this.getAllProducts();
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  editProduct(id) {
    this.router.navigate(['admin/product_admin/form/', { id: id }]);
  }
}
