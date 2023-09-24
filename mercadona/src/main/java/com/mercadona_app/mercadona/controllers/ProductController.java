package com.mercadona_app.mercadona.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona_app.mercadona.models.Product;
import com.mercadona_app.mercadona.services.ProductService;

// import java.util.Map;

@RestController
@RequestMapping("admin/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("add")
  public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }



}
