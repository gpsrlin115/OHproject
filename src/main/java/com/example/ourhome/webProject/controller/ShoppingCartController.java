package com.example.ourhome.webProject.controller;

import com.example.ourhome.webProject.model.CartItem;
import com.example.ourhome.webProject.service.ShoppingCartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/cartpage")
    public String showCart(Model model) {
        List<CartItem> cartItems = shoppingCartService.findAllCartItems();
        model.addAttribute("cartItems", cartItems);
        return "pagecart";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestParam("productId") Long productId) {
        try {
            shoppingCartService.addProductToCart(productId); // 서비스를 통해 cartitem에 상품 추가
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateCartItem(@RequestParam("id") Long itemId, @RequestParam("change") int change) {
        try {
            // 장바구니 아이템 수량 업데이트 로직
            shoppingCartService.updateQuantity(itemId, change);

            // 성공 응답 (예시)
            return "{\"status\":\"success\", \"message\":\"Quantity updated\"}";
        } catch (Exception e) {
            // 실패 응답 (예시)
            return "{\"status\":\"error\", \"message\":\"Error updating quantity\"}";
        }
    }
}
