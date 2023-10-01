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

import com.mercadona_app.mercadona.models.Promotion;
import com.mercadona_app.mercadona.services.PromotionService;

@RestController
@RequestMapping("api/promotion")
public class promotionController {
  
  @Autowired
  PromotionService promotionService;

  @PostMapping({"add"})
  public Promotion addNewPromotion(@RequestBody Promotion promotion) {
    return promotionService.addNewPromotion(promotion);
  }

  @GetMapping({"getAll"})
  public List<Promotion> getAllPromotions(){
    return promotionService.getAllPromotions();
  }

  @GetMapping({"get/{id}"})
  public Promotion getPromotionById(@PathVariable("id") Integer id){
    return promotionService.getPromotionDetailsById(id);
  }

  @DeleteMapping({"delete/{id}"})
  public void deletePromotionDetails(@PathVariable("id") Integer id) {
      promotionService.deletePromotionDetails(id);
  }
}
