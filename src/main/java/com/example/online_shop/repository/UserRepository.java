package com.example.online_shop.repository;

import com.example.online_shop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(value = "user")
    @Query("SELECT u FROM User u WHERE u.login = ?1")
    Optional<User> findUserByLoginOptional(String login);

    User findUserByNameAndLogin(String name, String login);

    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.id=:id")
    void deleteById(@Param("id") Integer id);

    @Query("SELECT u FROM User u WHERE u.role.name =?1")
    Page<User> findUsersByRoleName(String roleName, Pageable pageable);
}
