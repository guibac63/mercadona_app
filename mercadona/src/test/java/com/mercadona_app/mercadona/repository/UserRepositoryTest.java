package com.mercadona_app.mercadona.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.mercadona_app.mercadona.models.Role;
import com.mercadona_app.mercadona.models.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
  
  @Autowired
  private UserRepository userRepository;

  @Test
  public void UserRepository_SaveAll_ReturnSavedUser() {

    UserEntity user = UserEntity.builder()
        .username("usertest")
        .password("test")
        .build();

    UserEntity savedUser = userRepository.save(user);

    Assertions.assertThat(savedUser).isNotNull();
    Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

  }

  @Test
  public void UserRepository_GetAll_ReturnMoreThenOneUser() {
    UserEntity user = UserEntity.builder()
        .username("usertest")
        .password("test")
        .build();

    UserEntity user2 = UserEntity.builder()
        .username("usertest2")
        .password("test2")
        .build();

    userRepository.save(user);
    userRepository.save(user2);

    List<Role> userList = userRepository.findAll();

    Assertions.assertThat(userList).isNotNull();
    Assertions.assertThat(userList.size()).isEqualTo(2);
  }

  @Test
  public void UserRepository_FindById_ReturnUser() {
    UserEntity user = UserEntity.builder()
        .username("usertest")
        .password("test")
        .build();

    userRepository.save(user);

    UserEntity userSave = userRepository.findById(user.getId()).get();

    Assertions.assertThat(userSave).isNotNull();
  }

  @Test
  public void UserRepository_UpdateUser_ReturnUserNotNullAndExpectedName() {
    UserEntity user = UserEntity.builder()
        .username("usertest")
        .password("test")
        .build();

    userRepository.save(user);

    UserEntity userSave = userRepository.findById(user.getId()).get();
    userSave.setName("USER");

    UserEntity updatedUser = userRepository.save(userSave);

    Assertions.assertThat(updatedUser.getName()).isNotNull();
    Assertions.assertThat(updatedUser.getName()).isEqualTo("USER");
  }

  @Test
  public void UserRepository_UserDelete_ReturnUserIsEmpty() {
    UserEntity user = UserEntity.builder()
        .username("usertest")
        .password("test")
        .build();

    userRepository.save(user);

    userRepository.deleteById(user.getId());
    Optional<UserEntity> userReturn = userRepository.findById(user.getId());

    Assertions.assertThat(userReturn).isEmpty();
  }
}
