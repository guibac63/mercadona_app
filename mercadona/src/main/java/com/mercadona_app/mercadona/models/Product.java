package com.mercadona_app.mercadona.models;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "promotion_id")
  private Promotion promotion;

}
