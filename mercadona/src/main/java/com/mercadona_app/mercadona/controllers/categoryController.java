package com.mercadona_app.mercadona.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona_app.mercadona.models.Category;
import com.mercadona_app.mercadona.services.CategoryService;

@RestController
@RequestMapping("admin/category")
public class categoryController {
  
  @Autowired
  CategoryService categoryService;

  @PostMapping({"add"})
  public Category addNewCategory(@RequestBody Category category) {
    return categoryService.addNewCategory(category);
  }


}
