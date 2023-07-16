package com.example.online_shop.repository;

import com.example.online_shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.productName=?1")
    Optional<Product> findProductByProductName(String name);

    @Query("SELECT p FROM Product p ORDER BY p.price")
    Page<Product> findProductsAndSortByPrice(Pageable pageable);
}
