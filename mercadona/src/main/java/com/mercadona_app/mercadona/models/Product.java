package com.mercadona_app.mercadona.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
  // @NotNull
  @JoinColumn(name = "category_id")
  private Category category;
}
