package com.example.ourhome.webProject.controller;

import com.example.ourhome.webProject.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity,
                            RedirectAttributes redirectAttributes) {
        shoppingCartService.addToCart(productId, quantity);
        redirectAttributes.addFlashAttribute("success", "Product added to cart successfully");
        return "redirect:/cart";
    }
}
