package com.mercadona_app.mercadona.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mercadona_app.mercadona.models.Promotion;
import com.mercadona_app.mercadona.repository.PromotionRepository;
import com.mercadona_app.mercadona.services.PromotionService;

@ExtendWith(MockitoExtension.class)
public class PromotionServiceTests {
  
  @Mock
  private PromotionRepository promotionRepository;

  @InjectMocks
  private PromotionService promotionService;

  @Test
  public void PromotionService_CreatePromotion_ReturnsPromotion() {
    
    Date beginningDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2023, 12, 30, 59, 59, 59);
    Date endingDate = calendar.getTime();

    Promotion promotion = Promotion.builder()
              .promotionName("Promotion one")
              .beginningDate(beginningDate)
              .endingDate(endingDate)
              .promotionPercentage(10)
              .build();

    when(promotionRepository.save(Mockito.any(Promotion.class))).thenReturn(promotion);

    Promotion savedPromotion = promotionService.addNewPromotion(promotion);

    Assertions.assertThat(savedPromotion).isNotNull();

  }

  @Test
  public void PromotionService_CreatePromotion_ReturnsAllPromotions() {

    List<Promotion> promotionList = new ArrayList<Promotion>();

    Date beginningDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2023, 12, 30, 59, 59, 59);
    Date endingDate = calendar.getTime();

    Promotion promotion = Promotion.builder()
        .promotionName("Promotion one")
        .beginningDate(beginningDate)
        .endingDate(endingDate)
        .promotionPercentage(10)
        .build();

    Calendar calendar2 = Calendar.getInstance();
    calendar2.set(2023, 12, 10, 59, 59, 59);
    Date endingDate2 = calendar.getTime();

    Promotion promotion2 = Promotion.builder()
        .promotionName("Promotion two")
        .beginningDate(beginningDate)
        .endingDate(endingDate2)
        .promotionPercentage(25)
        .build();

    promotionList.add(promotion);
    promotionList.add(promotion2);

    when(promotionRepository.findAll()).thenReturn(promotionList);

    List<Promotion> savedCategoryList = promotionService.getAllPromotions();

    Assertions.assertThat(savedCategoryList).isNotNull();
    assertEquals(2, savedCategoryList.size());
    verify(promotionRepository, times(1)).findAll();

  }

  @Test
  public void PromotionService_CreatePromotion_ReturnsPromotionById() {

    Date beginningDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2023, 12, 30, 59, 59, 59);
    Date endingDate = calendar.getTime();

    Promotion promotion = Promotion.builder()
              .promotionName("Promotion one")
              .beginningDate(beginningDate)
              .endingDate(endingDate)
              .promotionPercentage(10)
              .build();

    promotionRepository.save(promotion);

    when(promotionRepository.findById(promotion.getId())).thenReturn(Optional.of(promotion));

    Promotion savedPromotion = promotionService.getPromotionDetailsById(promotion.getId());

    Assertions.assertThat(savedPromotion).isNotNull();
    Assertions.assertThat(savedPromotion.getPromotionName()).isEqualTo("Promotion one");

  }

  @Test
  public void PromotionService_DeletePromotion_ReturnsDeleteById() {

    int promotionId = 1;
    
    Date beginningDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.set(2023, 12, 30, 59, 59, 59);
    Date endingDate = calendar.getTime();

    Promotion promotion = Promotion.builder()
              .id(promotionId)
              .promotionName("Promotion one")
              .beginningDate(beginningDate)
              .endingDate(endingDate)
              .promotionPercentage(10)
              .build();

    assertAll(() -> promotionService.deletePromotionDetails(promotionId));

  }

}
