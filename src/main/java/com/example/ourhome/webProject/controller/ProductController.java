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

    // Constructor with dependency injection of the ProductService
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Fetch the products from the database through the service
        List<Product> products = productService.getAllProducts();
        // Add products to the model
        model.addAttribute("products", products);
        // Return the Thymeleaf template name
        return "index";
    }
}
