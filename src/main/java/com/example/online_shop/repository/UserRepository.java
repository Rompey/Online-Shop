package com.example.online_shop.repository;

import com.example.online_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.login = ?1")
    Optional<User> findUserByLoginOptional(String login);

    User findUserByLogin(String login);
}
