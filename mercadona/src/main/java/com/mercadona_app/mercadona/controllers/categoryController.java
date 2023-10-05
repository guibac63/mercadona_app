package com.mercadona_app.mercadona.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona_app.mercadona.models.Category;
import com.mercadona_app.mercadona.services.CategoryService;


@RestController
@RequestMapping("api/category")
public class categoryController {
  
  @Autowired
  CategoryService categoryService;


  @PostMapping({"add"})
  public Category addNewCategory(@RequestBody Category category) {
    return categoryService.addNewCategory(category);
  }

  @GetMapping({"getAll"})
  public List<Category> getAllCategories(){
    return categoryService.getAllCategories();
  }

  @GetMapping({"get/{id}"})
  public Category getCategoryById(@PathVariable("id") Integer id){
    return categoryService.getCategoryDetailsById(id);
  }

  @DeleteMapping({"delete/{id}"})
  public void deleteCategoryDetails(@PathVariable("id") Integer id) {
      categoryService.deleteCategoryDetails(id);
  }

}
