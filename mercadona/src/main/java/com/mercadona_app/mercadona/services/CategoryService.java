package com.mercadona_app.mercadona.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadona_app.mercadona.models.Category;
import com.mercadona_app.mercadona.repository.CategoryRepository;

@Service
public class CategoryService {
  
  @Autowired
  private CategoryRepository categoryRepository;


  public Category addNewCategory(Category category) {
    return categoryRepository.save(category);
  }


}
