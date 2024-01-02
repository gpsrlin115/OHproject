package com.example.ourhome.webProject.service;

import com.example.ourhome.webProject.model.CartItem;
import com.example.ourhome.webProject.repository.CartItemRepository;
import com.example.ourhome.webProject.repository.ProductRepository;
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

    public void addProductToCart(Long productId, int quantity) {
        // CartItem 객체 생성 및 설정
        CartItem cartItem = new CartItem();
        cartItem.setId(productId);
        cartItem.setQuantity(quantity);
        // 데이터베이스에 저장
        cartItemRepository.save(cartItem);
    }

}


