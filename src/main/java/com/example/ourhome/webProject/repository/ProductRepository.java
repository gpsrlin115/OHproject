package com.example.ourhome.webProject.repository;

import com.example.ourhome.webProject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
