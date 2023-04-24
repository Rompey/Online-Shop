package com.example.online_shop.repository;

import com.example.online_shop.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.login = ?1")
    Optional<User> findUserByLoginOptional(String login);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isDeleted=true WHERE u.login=?1")
    void deleteUserByLogin(String login);
}
