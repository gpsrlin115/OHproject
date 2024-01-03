package com.example.ourhome.webProject.controller;

import com.example.ourhome.webProject.model.Product;
import com.example.ourhome.webProject.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private final ProductService productService;

    // ProductService 의존성 주입
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // 서비스를 통해 데이터베이스에서 제품 가져오기
        List<Product> products = productService.getAllProducts();
        // 모델에 제품 주입
        model.addAttribute("products", products);
        // Thymeleaf 템플릿 이름 리턴
        return "index";
    }
}
