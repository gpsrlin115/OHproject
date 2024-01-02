package com.example.ourhome.webProject.service;

import com.example.ourhome.webProject.model.CartItem;
import com.example.ourhome.webProject.model.Product;
import com.example.ourhome.webProject.repository.CartItemRepository;
import com.example.ourhome.webProject.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class ShoppingCartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductToCart(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product); // 여기서 Product 객체를 설정.
        cartItem.setQuantity(1);
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> findAllCartItems() {
        return cartItemRepository.findAll();
    }

    public void updateQuantity(Long itemId, int change) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart item Name: " + itemId));
        cartItem.setQuantity(cartItem.getQuantity() + change);
        cartItemRepository.save(cartItem);
    }
}


