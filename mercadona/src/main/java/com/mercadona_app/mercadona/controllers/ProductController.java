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

import com.mercadona_app.mercadona.models.Product;
import com.mercadona_app.mercadona.services.ProductService;

// import java.util.Map;

@RestController
@RequestMapping("api/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("add")
  public Product addNewProduct(@RequestBody Product product) {
    return productService.addNewProduct(product);
  }

  @GetMapping({"getAll"})
  public List<Product> getAllProducts(){
    return productService.getAllProducts();
  }

  @GetMapping({"get/{id}"})
  public Product getProductById(@PathVariable("id") Integer id){
    return productService.getProductDetailsById(id);
  }

  @DeleteMapping({"delete/{id}"})
  public void deleteProductDetails(@PathVariable("id") Integer id) {
      productService.deleteProductDetails(id);
  }

}
