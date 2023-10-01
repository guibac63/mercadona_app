package com.mercadona_app.mercadona.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadona_app.mercadona.models.Category;
import com.mercadona_app.mercadona.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;


  public Category addNewCategory(Category category) {
    return categoryRepository.save(category);
  }


  public List<Category> getAllCategories() {
    return (List<Category>) categoryRepository.findAll();
  }

  public void deleteCategoryDetails(Integer id) {
    categoryRepository.deleteById(id);
  }

  public Category getCategoryDetailsById(Integer id) {
    return categoryRepository.findById(id).get();
  }


}
