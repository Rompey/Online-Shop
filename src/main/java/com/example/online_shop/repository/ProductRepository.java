package com.example.online_shop.repository;

import com.example.online_shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p")
    Page<Product> findAllProductsInPageFormat(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productName=?1")
    Product findProductByProductName(String name);
}
