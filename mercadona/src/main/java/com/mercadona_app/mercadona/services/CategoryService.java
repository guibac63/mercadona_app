package com.mercadona_app.mercadona.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mercadona_app.mercadona.models.Category;
import com.mercadona_app.mercadona.models.Product;
import com.mercadona_app.mercadona.models.UserEntity;
import com.mercadona_app.mercadona.repository.CategoryRepository;
import com.mercadona_app.mercadona.repository.UserRepository;

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

  @Autowired
  UserRepository userRepository;

  public Category addNewCategory(Category category) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if(auth != null){  
      Optional<UserEntity> userOptional = userRepository.findByUsername(auth.getName());
      if(userOptional.isPresent()) {
        UserEntity user = userOptional.get();
        category.setUserEntity(user);
        category.setWritingDate(new Date());
        Category savedCategory = categoryRepository.save(category);
        savedCategory.setUserEntity(null);
        return savedCategory;
      } else {
        throw new RuntimeException("Utilisateur non trouvé pour le nom d'utilisateur : " + auth.getName());
      }
    }else {
      return categoryRepository.save(category);
    }
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
