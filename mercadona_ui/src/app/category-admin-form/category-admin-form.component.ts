import { Component, OnInit } from '@angular/core';
import { Category } from '../_model/category.model';
import { NgForm } from '@angular/forms';
import { CategoryService } from '../_services/category.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-category-admin-form',
  templateUrl: './category-admin-form.component.html',
  styleUrls: ['./category-admin-form.component.css'],
})
export class CategoryAdminFormComponent implements OnInit {
  category: Category = {
    categoryName: '',
  };

  constructor(private categoryService: CategoryService) {}

  ngOnInit(): void {}

  addCategory(categoryForm: NgForm) {
    this.categoryService.addCategory(this.category).subscribe({
      
        next: (response:Category) => {
          console.log(response);
        },
        error: (error) => {
          console.log(error);
        },
  });
  }
}
