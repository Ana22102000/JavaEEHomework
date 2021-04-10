package com.example.demo.Database.Repository;

import com.example.demo.Database.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("SELECT user FROM UserEntity user "
            + "LEFT JOIN FETCH user.permissions "
            + "WHERE user.login = :login")
    Optional<UserEntity> findByLogin(@Param("login") String login);

    @Query("SELECT user FROM UserEntity user "
            + "LEFT JOIN FETCH user.permissions "
            + "LEFT JOIN FETCH user.selectedBooks "
            + "WHERE user.login = :login")
    Optional<UserEntity> findByLoginWithBooks(@Param("login") String login);


  //  @Query("INSERT INTO selected_books (user_id, book_id) " +
  //          "VALUES (:user_id, :book_id)")
  //  Boolean addselectedBook(@Param("user_id") String userId, @Param("book_id") String bookId);


}