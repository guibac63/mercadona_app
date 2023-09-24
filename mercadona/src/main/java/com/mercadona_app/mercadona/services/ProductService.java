package com.mercadona_app.mercadona.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadona_app.mercadona.models.Product;
import com.mercadona_app.mercadona.repository.ProductRepository;

@Service
public class ProductService {
  
  @Autowired
  private ProductRepository productRepository;

  public Product createProduct(Product product) {
      return productRepository.save(product);
  }
}
