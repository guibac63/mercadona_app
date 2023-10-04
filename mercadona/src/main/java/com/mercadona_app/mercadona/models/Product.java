package com.mercadona_app.mercadona.models;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int id;

  private String productLibelle;

  private String productDescription;

  private Double productPrice;

  private double productDiscountedPrice;

   private Date writingDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "promotion_id")
  private Promotion promotion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

}
