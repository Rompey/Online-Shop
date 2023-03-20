package com.example.online_shop.repository;

import com.example.online_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserById(Integer id);
}
