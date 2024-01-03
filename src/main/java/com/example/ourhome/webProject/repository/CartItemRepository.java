package com.example.ourhome.webProject.repository;

import com.example.ourhome.webProject.model.CartItem;
import com.example.ourhome.webProject.model.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByProduct(Product product);
}
