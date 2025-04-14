package com.neosnakex34.springstore.repository;

import com.neosnakex34.springstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
