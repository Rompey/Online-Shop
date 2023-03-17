package com.example.online_shop.repository;

import com.example.online_shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role findRoleByName(String name);

    @Query("SELECT r FROM Role r WHERE r.id = ?1")
    Role findRoleById(Integer id);
}
