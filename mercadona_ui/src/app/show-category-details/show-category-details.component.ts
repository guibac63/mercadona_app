import { Component,OnInit,ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CategoryService } from '../_services/category.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-show-category-details',
  templateUrl: './show-category-details.component.html',
  styleUrls: ['./show-category-details.component.css'],
})
export class ShowCategoryDetailsComponent implements OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;

  categoryDetails: any;
  displayedColumns: string[] = ['id', 'categoryName', 'edit'];

  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllCategories();
  }

  public getAllCategories() {
    this.categoryService.getAllCategories().subscribe({
      next: (response: any) => {
        this.categoryDetails = new MatTableDataSource(response);
        this.categoryDetails.paginator = this.paginator;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  deleteProduct(categoryId) {
    this.categoryService.deleteCategory(categoryId).subscribe({
      next: (response) => {
        console.log(response);
        this.getAllCategories();
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  editProduct(id){
    this.router.navigate(['admin/category_admin/form/',{id: id}]);
  }
}