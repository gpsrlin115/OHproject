package com.example.ourhome.webProject.service;

import com.example.ourhome.webProject.model.CartItem;
import com.example.ourhome.webProject.model.Product;
import com.example.ourhome.webProject.repository.CartItemRepository;
import com.example.ourhome.webProject.repository.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductToCart(Long productId, int quantity) {
        Optional<Product> productOpt = productRepository.findById(productId); // 상품 ID로 상품 찾기
        if (!productOpt.isPresent()) {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + productId);
        }
        Product product = productOpt.get();
        //장바구니에 해당 상품이 있는지 확인
        Optional<CartItem> existingCartItemOpt = cartItemRepository.findByProduct(product);
        CartItem cartItem;
        if (existingCartItemOpt.isPresent()){// 상품이 있으면 수량만 증가
            cartItem = existingCartItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else { // 상품이 없으면 새로운 CartItem 생성
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
    }

    public void addToCart(Long productId, int quantity) {
        CartItem cartItem = new CartItem();
        // Set product reference and quantity
        cartItemRepository.save(cartItem);
    }
}


