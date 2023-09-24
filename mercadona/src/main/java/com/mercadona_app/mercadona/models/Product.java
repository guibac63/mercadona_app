package com.mercadona_app.mercadona.models;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
public class Product {
  

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private int id;

  private String productLibelle;
  private String productDescription;
  private Double productPrice;
  private double productDiscountedPrice;
}
