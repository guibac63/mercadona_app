package com.mercadona_app.mercadona.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercadona_app.mercadona.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
  Optional<UserEntity> findByUsername(String username);
  Boolean existsByUsername(String username);
}
