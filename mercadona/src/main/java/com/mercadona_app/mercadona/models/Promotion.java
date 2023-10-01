package com.mercadona_app.mercadona.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private int id;

  private String promotionName;


  private Date beginningDate; 


  private Date endingDate;

  @Min(value = 1, message = "La valeur doit être d'au moins 1")
  @Max(value = 99, message = "La valeur doit être au maximum 99")
  private int promotionPercentage;

  @OneToMany(mappedBy = "promotion")
    @JsonIgnore
    private Set<Product> products;




  
}
