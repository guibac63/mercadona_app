package com.mercadona_app.mercadona.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadona_app.mercadona.models.Category;
import com.mercadona_app.mercadona.models.Promotion;
import com.mercadona_app.mercadona.repository.PromotionRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionService {
    
  @Autowired
  private PromotionRepository promotionRepository;
  
  public Promotion addNewPromotion(Promotion promotion){
    return promotionRepository.save(promotion);
  }

  public List<Promotion> getAllPromotions() {
    return (List<Promotion>) promotionRepository.findAll();
  }

  public void deletePromotionDetails(Integer id) {
    promotionRepository.deleteById(id);
  }

  public Promotion getPromotionDetailsById(Integer id) {
    return promotionRepository.findById(id).get();
  }

}
