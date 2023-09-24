package com.mercadona_app.mercadona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadona_app.mercadona.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
